package io.quinio.transaction.bean;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

/**
 * @author Luis Angel Rodriguez Fitta
 * Objeto originDetails
 */
@Data
public class OriginBean {
	/**
	 * Campo tax
	 */
	private Double tax;
	/**
	 * Campo  group
	 */
	private String group;
	/**
	 * Campo  store
	 */
	private String store;
	/**
	 * Campo total
	 */
	private Double total;
	/**
	 * Campos details
	 */
	private List<DetailBean> details;
	/**
	 * Campo cardcode
	 */
	private String cardcode;
	/**
	 * Campo cardname
	 */
	private String cardname;
	/**
	 * Campo payment
	 */
	private List<PaymentBean> payments;
	/**
	 * Campo subtotal
	 */
	private Double subtotal;
	/**
	 * Campo actualdate
	 */
	private Timestamp actualdate;
	/**
	 * Campo businessdate
	 */
	private Timestamp businessdate;
	/**
	 * Campo transactionkey
	 */
	private String transactionkey;
}
