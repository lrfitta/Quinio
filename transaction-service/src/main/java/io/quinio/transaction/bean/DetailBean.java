package io.quinio.transaction.bean;

import java.math.BigDecimal;

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
	private BigDecimal qty;
	/**
	 * Campo tax
	 */
	private BigDecimal tax;
	/**
	 * Campo type
	 */
	private String type;
	/**
	 * Campo total
	 */
	private BigDecimal total;
	/**
	 * Campo category
	 */
	private String category;
	/**
	 * Campo discount
	 */
	private BigDecimal discount;
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
	private BigDecimal subtotal;
	/**
	 * Campo subtotal
	 */
	private String transactionkey;
}	
