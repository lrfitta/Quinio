package io.quinio.transaction.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.quinio.transaction.dao.IMuVentaDAO;
import io.quinio.transaction.exception.QuinioException;
import io.quinio.transaction.model.MuVentaTransactionResponseBean;
import io.quinio.transaction.model.ReportBean;
import io.quinio.transaction.model.ReportRequestBean;
import io.quinio.transaction.model.ReportResponseBean;
import io.quinio.transaction.model.ResponseBean;
import io.quinio.transaction.model.TransactionBean;
import io.quinio.transaction.openEnum.CodeErrorEnum;
import io.quinio.transaction.openEnum.ResultEnum;
import io.quinio.transaction.repository.IReportRepository;
import io.quinio.transaction.repository.ITransactionRepository;
import io.quinio.transaction.service.ICalendarService;
import io.quinio.transaction.service.IGraphService;
import io.quinio.transaction.service.ITransactionService;
import io.quinio.transaction.utils.Constants;

/**
 * @author Luis Angel Rodriguez Fitta Implementacion de la interfaz
 *         ITransactionService
 */
@Service
public class TransactionService implements ITransactionService {
	/**
	 * Logger
	 */
	private final static Logger LOGGER = LogManager.getLogger(TransactionService.class);

	@Autowired
	private ITransactionRepository transactionRepository;
	@Autowired
	private IMuVentaDAO muVentaDao;
	@Autowired
	private IReportRepository reportRepository;
	@Autowired
	private ICalendarService calendarService;
	@Autowired
	private IGraphService graphService;

	/**
	 * Metodo para recuperar las transacciones del servicio y guardarlas en la bd.
	 * 
	 * @throws QuinioException
	 */
	@Override
	public ResponseBean generateReport() {
		ResponseBean response = new ResponseBean();
		response.setResult(ResultEnum.SUCCESS);
		int page = 0;
		boolean flag = true;
		MuVentaTransactionResponseBean responseMuVenta = null;
		LOGGER.info("Se inicia la generacion del reporte ");
		List<ReportBean> reports = new ArrayList<ReportBean>();
		try {
			while (flag) {
				responseMuVenta = extractTransactions(page, reports);
				flag = !responseMuVenta.getLast();
				page += 1;
			}
			reportRepository.saveAll(reports);
		} catch (QuinioException exception) {
			LOGGER.error("Error: ", exception);
			response.setResult(ResultEnum.ERROR);
			response.setDescription(exception.getMessage());
			response.setCodeError(exception.getCode());
		}
		LOGGER.info(responseMuVenta.getTotalElements());
		LOGGER.info("Finaliza la generacion del reporte");
		return response;
	}

	/**
	 * Metodo que realiza la peticion get al servicio de muventa
	 * 
	 * @param page Numero de pagina a consultar
	 * @return Response al consultar el servicio de consulta de transacciones
	 * @throws QuinioException
	 */
	private MuVentaTransactionResponseBean extractTransactions(final int page, final List<ReportBean> reports)
			throws QuinioException {
		MuVentaTransactionResponseBean response = muVentaDao.getTransactions(page,
				Constants.DEFAULT_NUMBER_OF_ROWS_TRANSACTION);
		if (response != null && !response.getEmpty()) {
			List<TransactionBean> transactions = response.getTransactions();
			transactions.forEach(transaction -> {
				List<TransactionBean> tmp = transactionRepository.findByIdTransaction(transaction.getIdTransaction());
				if (tmp.isEmpty()) {
					transactionRepository.save(transaction);
					processTransaction(transaction, reports);
				}
			});
		}
		return response;
	}

	/**
	 * Procesa la transacion
	 * 
	 * @param transaction Transaccion a procesar
	 * @param reports     Lista de reportes
	 */
	private void processTransaction(final TransactionBean transaction, final List<ReportBean> reports) {
		ReportBean report = calculateDates(transaction.getCreatedAt(), reports);
		String type = StringUtils.trim(transaction.getType());

		Double amount = transaction.getRewardAmount() == null ? 0.0 : transaction.getRewardAmount();
		if (Constants.TYPE_BONUS.equals(type) || Constants.TYPE_BONUS_ADMIN.equals(type)) {
			Double salesAmount = transaction.getSaleAmount() == null ? 0.0 : transaction.getSaleAmount();
			report.setBonusTransaction(report.getBonusTransaction() + 1);
			report.setSales(new BigDecimal(report.getSales() + salesAmount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			report.setBonusAmount(new BigDecimal(report.getBonusAmount() + amount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		

		if (Constants.TYPE_REDEEM.equals(type)) {
			report.setRedemptionTransaction(report.getRedemptionTransaction() + 1);
			report.setRedeemedAmount(new BigDecimal(report.getRedeemedAmount() + amount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		}

		if (Constants.TYPE_EXPIRATION.equals(type)) {
			report.setExpireTransaction(report.getExpireTransaction() + 1);
			report.setExpireAmount(new BigDecimal(report.getExpireAmount() + amount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		calculateAvailableBalance(report);
	}

	/**
	 * Calcula el saldo disponible
	 * 
	 * @param report reporte a calcular
	 */
	private void calculateAvailableBalance(ReportBean report) {
		Calendar calendar = calendarService.createCalendarTimeZone(report.getStartWeek());
		Calendar lastWeek = calendarService.getLastWeek(calendar);
		List<ReportBean> reportsLastWeek = reportRepository.findByStartWeek(lastWeek);
		Double balanceLastWeek = 0.0;
		if (reportsLastWeek != null && !reportsLastWeek.isEmpty()) {
			balanceLastWeek = reportsLastWeek.get(0).getAvailableBalance();
		}
		report.setAvailableBalance(new BigDecimal(report.getBonusAmount()
				- report.getExpireAmount() + report.getRedeemedAmount() + balanceLastWeek).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}

	/**
	 * Busca un reporte por numero de semana
	 * 
	 * @param reports      Lista de reportes
	 * @param numberOfWeek numero de semana a buscar
	 * @return si el reporte esta en la lista
	 */
	private ReportBean getReportByNumberOfWeek(List<ReportBean> reports, final int numberOfWeek) {
		ReportBean result = null;
		Optional<ReportBean> optional = reports.stream().filter(report -> (report.getNumberWeek() == numberOfWeek))
				.findFirst();
		if (optional.isPresent()) {
			result = optional.get();
		}
		return result;
	}

	/**
	 * Calcula las fechas iniciales de la transacion
	 * 
	 * @param date Fecha de creacion
	 * @return Objeto del tipo report con la fecha inicio y numero de semana
	 */
	private ReportBean calculateDates(Date date, final List<ReportBean> reports) {
		Calendar createdAt = calendarService.createCalendarTimeZone(date);
		int numberOfWeek = createdAt.get(Calendar.WEEK_OF_YEAR);
		ReportBean report = getReportByNumberOfWeek(reports, numberOfWeek);
		if (report != null) {
			return report;
		}
		Calendar firstDay = calendarService.getFirstDayOfWeek(createdAt);
		report = new ReportBean();
		report.setNumberWeek(numberOfWeek);
		report.setStartWeek(firstDay.getTime());
		reports.add(report);
		return report;
	}

	/**
	 * Busca los reportes asociados
	 * 
	 * @param page numero de pagina
	 * @param size Numero de registros
	 * @return
	 */
	@Override
	public ReportResponseBean getReport(final ReportRequestBean request) {
		ReportResponseBean response = new ReportResponseBean();
		String start = request.getStartDate();
		String end = request.getEndDate();
		Calendar startDate = Calendar.getInstance(TimeZone.getTimeZone(Constants.DEFAULT_TIME_ZONE));
		Calendar endDate = Calendar.getInstance(TimeZone.getTimeZone(Constants.DEFAULT_TIME_ZONE));
		LOGGER.info("Page:" + request.getPage());
		LOGGER.info("Size:" + request.getSize());
		LOGGER.info("Start Date: " + request.getStartDate());
		LOGGER.info("End Date: " + request.getEndDate());
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.FORMAT_DATE_GRAPH);
		dateFormat.setTimeZone(TimeZone.getTimeZone(Constants.DEFAULT_TIME_ZONE));
		if (request.getPage() == null) {
			request.setPage(0);
		}
		if (request.getSize() == null) {
			request.setSize(Constants.DEFAULT_NUMBER_OF_ROWS_REPORT);
		}
		try {
			if (!StringUtils.isBlank(start)) {
				startDate.setTime(dateFormat.parse(start));
			}
			if (!StringUtils.isBlank(end)) {
				endDate.setTime(dateFormat.parse(end));
			}
			response = generateReportData(request.getPage(), request.getSize(), startDate.getTime(), endDate.getTime());
		} catch(Exception exception) {
			LOGGER.error(exception);
			response.setCodeError(CodeErrorEnum.FORMAT_DATE);
			response.setDescription("El formato de las fechas es incorrecto");
			response.setResult(ResultEnum.ERROR);
		}
		return response;
	}
	
	/**
	 * @param page Numero de pagina
	 * @param size Numero de elementos
	 * @param startDate Fecha de inicio
	 * @param endDate Fecha de fin
	 */
	private ReportResponseBean generateReportData(final Integer page, final Integer size, Date startDate, Date endDate) {
		ReportResponseBean response = new ReportResponseBean();
		response.setResult(ResultEnum.SUCCESS);
		Pageable paging = PageRequest.of(page, size);
		
		Integer tmp = reportRepository.findByRangeDateCount(startDate, endDate);
		Integer numberOfPages = tmp / size + (tmp % size == 0 ? 0 : 1);
		response.setNumberOfPages(numberOfPages);
		
		Page<ReportBean> pagina = reportRepository.findByRangeDate(startDate, endDate, paging);
		if (pagina.hasContent()) {
			List<ReportBean> data = pagina.getContent();
			response.setData(data);
			response.setDaily(graphService.daily(data));
			response.setWeekly(graphService.weekly(data));
		} else {
			response.setCodeError(CodeErrorEnum.NO_DATA_REPORT);
			response.setResult(ResultEnum.ERROR);
			response.setDescription("No hay registros con la informacion proporcionada");
		}
		LOGGER.info("Numero de registros: " + pagina.getContent().size());
		return response;
	}
}
