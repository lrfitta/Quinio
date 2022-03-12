package io.quinio.transaction.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.quinio.transaction.model.GraphBean;
import io.quinio.transaction.model.ReportBean;
import io.quinio.transaction.model.TransactionBean;
import io.quinio.transaction.repository.ITransactionRepository;
import io.quinio.transaction.service.IGraphService;
import io.quinio.transaction.service.IHelperService;
import io.quinio.transaction.utils.Constants;

/**
 * @author Luis Angel Rodriguez Fitta
 * Implementacion de la interfaz IGraphService
 */
@Service
public class GraphService implements IGraphService {
	
	@Autowired
	private IHelperService helperService;
	@Autowired
	private ITransactionRepository transactionRepository;
	/**
	 * Genera la informacion para la creacion de la grafica semanalmente
	 * @param data Informacion para generar el reporte
	 * @return Map con la informaicon de la grafica
	 */
	@Override
	public List<GraphBean> weekly(List<ReportBean> data) {
		List<GraphBean> graph = new ArrayList<GraphBean>();
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.FORMAT_DATE_GRAPH);
		graph = data.stream().map(val -> {
			GraphBean bean = new GraphBean();
			bean.setLabel(dateFormat.format(val.getStartWeek()));
			bean.setValue(val.getBonusTransaction());
			return bean;
		}).collect(Collectors.toList());
		return graph;
	}
	
	/**
	 * Genera la informacion para la creacion de la grafica diariamente
	 * @param data Informacion para generar el reporte
	 * @return Map con la informaicon de la grafica
	 */
	@Override
	public List<GraphBean> daily(List<ReportBean> data) {
		List<GraphBean> graph = new ArrayList<GraphBean>();
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.FORMAT_DATE_GRAPH);
		
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		List<TransactionBean> transactions = null;
		for(ReportBean dato : data) {
			start.setTime(dato.getStartWeek());
			start = helperService.setTimeZero(start);
			end = helperService.getEndDayOfWeek(start);
			
			transactions = transactionRepository.findGraphDaily(start.getTime(), end.getTime());
			transactions.forEach(transaction -> {
				mapToBean(graph, transaction, dateFormat);
			});
		}
	
		return graph;
	}
	
	/**
	 * @param graph Lista de graficas
	 * @param transaction Transaccion a procesar
	 * @param dateFormat formato de fecha
	 * @return
	 */
	private void mapToBean(List<GraphBean> graph, TransactionBean transaction, SimpleDateFormat dateFormat) {
		String label = null;
		label = dateFormat.format(transaction.getCreatedAt());
		GraphBean bean = getGraph(graph, label);
		if(bean == null) {
			bean = new GraphBean();
			bean.setLabel(label);
			graph.add(bean);
		}
		bean.setValue(bean.getValue() + 1);
	}
	
	/**
	 * @param graph Lista de graficas
	 * @param label Label a buscar
	 * @return
	 */
	private GraphBean getGraph(List<GraphBean> graph, String label) {
		GraphBean bean = null;
		Optional<GraphBean> optional = graph.stream().filter(g -> g.getLabel().equals(label)).findFirst();
		if(optional.isPresent()) {
			bean = optional.get();
		}
		return bean;
	}

}
