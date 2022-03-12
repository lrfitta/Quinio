package io.quinio.transaction.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * @author Luis Angel Rodriguez Fitta
 * Mapeo de la coleccion Report
 */
@Data
@Document(collection = "Report")
public class ReportBean {
	
	public ReportBean() {
		this.bonusTransaction = 0;
		this.sales = 0.0;
		this.bonusAmount = 0.0;
		this.redemptionTransaction = 0;
		this.redeemedAmount = 0.0;
		this.expireTransaction = 0;
		this.expireAmount = 0.0;
		this.availableBalance = 0.0;
	}
	
	/**
	 * Column id
	 */
	@Id
	private String id;
	/**
	 * Column startWeek
	 */
	private Date startWeek;
	/**
	 * Column numberWeek
	 */
	private Integer numberWeek;
	/**
	 * Column bonusTransaction
	 */
	private Integer bonusTransaction;
	/**
	 * Column sales
	 */
	private Double sales;
	/**
	 * Column bonusAmount
	 */
	private Double bonusAmount;
	/**
	 * Column redemptionTransaction
	 */
	private Integer redemptionTransaction;
	/**
	 * Column redeemedAmount
	 */
	private Double redeemedAmount;
	/**
	 * Column expireTransaction
	 */
	private Integer expireTransaction;
	/**
	 * Column expireAmount
	 */
	private Double expireAmount;
	/**
	 * Column availableBalance
	 */
	private Double availableBalance;
}
