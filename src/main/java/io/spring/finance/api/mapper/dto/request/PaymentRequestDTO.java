package io.spring.finance.api.mapper.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentRequestDTO {

	private Long idDebitForPayment;
	private LocalDate receivement;
	private BigDecimal amount;
	private BigDecimal fees;
	private BigDecimal fine;
	private BigDecimal discount;
	public Long getIdDebitForPayment() {
		return idDebitForPayment;
	}
	public void setIdDebitForPayment(Long idDebitForPayment) {
		this.idDebitForPayment = idDebitForPayment;
	}
	public LocalDate getReceivement() {
		return receivement;
	}
	public void setReceivement(LocalDate receivement) {
		this.receivement = receivement;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getFees() {
		return fees;
	}
	public void setFees(BigDecimal fees) {
		this.fees = fees;
	}
	public BigDecimal getFine() {
		return fine;
	}
	public void setFine(BigDecimal fine) {
		this.fine = fine;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	
}