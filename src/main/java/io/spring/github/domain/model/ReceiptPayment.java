package io.spring.github.domain.model;

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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "payment")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ReceiptPayment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_debit")
	private Payment debitForPayment;

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
}