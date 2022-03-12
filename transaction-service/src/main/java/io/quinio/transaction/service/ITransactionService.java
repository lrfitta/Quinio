package io.quinio.transaction.service;

import java.util.List;

import io.quinio.transaction.exception.QuinioException;
import io.quinio.transaction.model.ReportBean;
import io.quinio.transaction.model.ResponseBean;

/**
 * @author Luis Angel Rodriguez Fitta
 * Servicio para el procesamiento de transacciones
 */
public interface ITransactionService {
	/**
	 * Metodo para generar el reporte de las transacciones
	 * @throws QuinioException
	 */
	public ResponseBean generateReport();
	
	/**
	 * Busca los reportes asociados
	 * @param page numero de pagina
	 * @param size Numero de registros
	 * @return
	 */
	public List<ReportBean> getReport(final Integer page, final Integer size);

}
