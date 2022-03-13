package io.quinio.transaction.model;

import lombok.Data;

/**
 * @author Luis Angel Rodriguez Fitta
 * Response cuando se genera el report
 */
@Data
public class GenerateReportResponseBean extends ResponseBean {
	/**
	 * Numero de registros generados
	 */
	private Integer weekGenerated;
	/**
	 * Numero de transacciones generadas
	 */
	private Integer transactions;
}
