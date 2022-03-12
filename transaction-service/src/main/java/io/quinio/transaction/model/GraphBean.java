package io.quinio.transaction.model;

import lombok.Data;

/**
 * @author Luis Angel Rodriguez Fitta
 * Bean para guardar el valor de las graficas
 */
@Data
public class GraphBean {
	
	public GraphBean() {
		this.value = 0;
	}
	/**
	 * Etiqueta de la grafica
	 */
	private String label;
	/**
	 * Valor de la grafica
	 */
	private Integer value;
}
