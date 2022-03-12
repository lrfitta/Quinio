package io.quinio.transaction.model;

import lombok.Data;

/**
 * @author Luis Angel Rodriguez Fitta
 * Objeto details
 */
@Data
public class DetailBean {
	/**
	 * Campo qty
	 */
	private Double qty;
	/**
	 * Campo tax
	 */
	private Double tax;
	/**
	 * Campo type
	 */
	private String type;
	/**
	 * Campo total
	 */
	private Double total;
	/**
	 * Campo category
	 */
	private String category;
	/**
	 * Campo discount
	 */
	private Double discount;
	/**
	 * Campo itemcode
	 */ 
	private String itemcode;
	/**
	 * Campo itemname
	 */
	private String itemname;
	/**
	 * Campo subtotal
	 */
	private Double subtotal;
	/**
	 * Campo subtotal
	 */
	private String transactionkey;
}	
