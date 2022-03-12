package io.quinio.transaction.model;

import java.util.Date;

import lombok.Data;

/**
 * @author Luis Angel Rodriguez Fitta
 * Request para obtener el reporte
 */
@Data
public class ReportRequestBean {
	/**
	 * Numero de pagina
	 */
	private Integer page;
	/**
	 * Numero de registros
	 */
	private Integer size;
	/**
	 * Fecha de inicio
	 */
	private Date startDate;
	/**
	 * Fecha de fin
	 */
	private Date endDate;
}
