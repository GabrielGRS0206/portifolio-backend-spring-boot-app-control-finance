package io.spring.finance.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "payment")
public class Payment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_debit")
	private DebitForPayment debitForPayment;

	@NotNull
	@NotBlank	
	private LocalDate receivement;

	@Column(precision = 15, scale = 2)
	private BigDecimal amount;

	@Column(precision = 15, scale = 2)
	private BigDecimal fees;

	@Column(precision = 15, scale = 2)
	private BigDecimal fine;

	@Column(precision = 15, scale = 2)
	private BigDecimal discount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DebitForPayment getDebitForPayment() {
		return debitForPayment;
	}

	public void setDebitForPayment(DebitForPayment debitForPayment) {
		this.debitForPayment = debitForPayment;
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