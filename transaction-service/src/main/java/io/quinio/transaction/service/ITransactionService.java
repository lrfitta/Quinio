package io.quinio.transaction.service;

import io.quinio.transaction.exception.QuinioException;
import io.quinio.transaction.model.GenerateReportResponseBean;
import io.quinio.transaction.model.ReportRequestBean;
import io.quinio.transaction.model.ReportResponseBean;

/**
 * @author Luis Angel Rodriguez Fitta
 * Servicio para el procesamiento de transacciones
 */
public interface ITransactionService {
	/**
	 * Metodo para generar el reporte de las transacciones
	 * @throws QuinioException
	 */
	public GenerateReportResponseBean generateReport();
	
	/**
	 * Busca los reportes asociados
	 * @param page numero de pagina
	 * @param size Numero de registros
	 * @return
	 */
	public ReportResponseBean getReport(final ReportRequestBean request);

}
