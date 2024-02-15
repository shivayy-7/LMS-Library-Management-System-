package com.aashdit.lms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;

@Entity
@Table(name  =  "t_lms_payment_transaction")
public class PaymentTransaction extends Auditable<User> implements Serializable {
private static final long serialVersionUID = -11804679874330L;

	@Id
	@GeneratedValue(strategy  =  GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Long transactionId;

	@Column(name = "amount")
	private Double amount;

	@NotNull
	@Column(name = "payment_id")
	private String paymentId;

	@NotNull
	@Column(name = "order_id")
	private String orderId;

	@Column(name = "signature")
	private String signature;

	@Column(name = "name")
	private String name;

	@NotNull
	@Column(name = "status")
	private String status;
	
	@Column(name="reason")
	private String reason;
	
	@Column(name="payment_mode")
	private String paymentMode;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "book_issued_id")
	private BookIssue bookIssue;

	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public BookIssue getBookIssue() {
		return bookIssue;
	}
	public void setBookIssue(BookIssue bookIssue) {
		this.bookIssue = bookIssue;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

}
