package io.quinio.transaction.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author Luis Angel Rodriguez Fitta
 * Mapeo de la coleccion Transaction
 */
@Data
@Document(collection = "Transaction")
public class TransactionBean {
	@Id
	@JsonIgnore
	private String id;
	/**
	 * Campo id
	 */
	@JsonProperty("id")
	private String idTransaction;
	/**
	 * Campo origin
	 */
	private String origin;
	/**
	 * Campo type
	 */
	private String type;
	/**
	 * Campo walletId
	 */
	private String walletId;
	/**
	 * Campo orderId
	 */
	private String orderId;
	/**
	 * Campo foreignTxId
	 */
	private String foreignTxId;
	/**
	 * Campo expenses
	 */
	private String expenses;
	/**
	 * Campo saleAmount
	 */
	private Double saleAmount;
	/**
	 * Campo rewardAmount
	 */
	private Double rewardAmount;
	/**
	 * Campo amountUsed
	 */
	private Double amountUsed;
	/**
	 * Campo expirationDate
	 */
	private Date expirationDate;
	/**
	 * Campo createdAt
	 */
	private Date createdAt;
	/**
	 * Campo status
	 */
	private String status;
	/** 
	 * Campo collaboratorId
	 */
	private String collaboratorId;
	/**
	 * Campo originDetails
	 */
	private OriginBean originDetails;
	/**
	 * Campo rewardsDetails
	 */
	private String rewardsDetails;
	/**
	 * Campo userId
	 */
	private String userId;
}
