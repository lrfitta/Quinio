package io.quinio.transaction.model;

import java.util.List;

import lombok.Data;

/**
 * @author Luis Angel Rodriguez Fitta
 * Respuesta para la creacion del reporte
 */
@Data
public class ReportResponseBean extends ResponseBean {
	/**
	 * Numero de paginas
	 */
	private Integer numberOfPages;
	/**
	 * Informacion del reporte
	 */
	private List<ReportBean> data;
	
	/**
	 * Informacion para la generacion de la grafica semanalmente
	 */
	private List<GraphBean> weekly;
	/**
	 * Informacion para la generacion de la grafica diariamente
	 */
	private List<GraphBean> daily;
}
