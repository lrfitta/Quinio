package io.quinio.transaction.bean;

import lombok.Data;

/**
 * @author Luis Angel Rodriguez Fitta
 * Payment Objeto
 */
@Data
public class PaymentBean {
	/**
	 * Campo type
	 */
	private String type;
	/**
	 * Campo amount
	 */
	private Double amount;
	/**
	 * Campo reference
	 */
	private String reference;
	/**
	 * Campo transactionkey
	 */
	private String transactionkey;
}
