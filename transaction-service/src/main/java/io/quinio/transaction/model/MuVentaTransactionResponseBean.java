package io.quinio.transaction.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author Luis Angel Rodriguez Fitta
 * Response del service transactions
 */
@Data
public class MuVentaTransactionResponseBean {
	/**
	 * Campo content
	 */
	@JsonProperty("content")
	private List<TransactionBean> transactions;
	
	/**
	 * Campo last
	 */
	private Boolean last;
	/**
	 * Campo totalPages
	 */
	private Integer totalPages;
	/**
	 * Campo totalElements
	 */
	private Integer totalElements;
	/**
	 * Campo empty
	 */
	private Boolean empty;
	/**
	 * Campo first
	 */
	private Boolean first;
}
