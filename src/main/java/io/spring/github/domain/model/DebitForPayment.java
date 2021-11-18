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

import com.google.common.base.Strings;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "debit_payment")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DebitForPayment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotBlank
	@Column(precision = 15, scale = 2)
	private BigDecimal value;

	@NotNull
	@NotBlank
	@Column(length = 5)
	private String parcel;

	@NotNull
	@NotBlank
	private LocalDate dueDate;

	@NotNull
	@NotBlank
	private LocalDate emission;

	@ManyToOne
	@JoinColumn(name = "id_company")
	private CompanyPerson company;

	@Column(length = 1)
	private String status;

	@NotNull
	@NotBlank
	@Column(length = 120)
	private String description;

	@NotNull
	@NotBlank
	@Column(length = 180)
	private String observation;

	public DebitForPayment(Long id) {
		this.id = id;
	}

	public DebitForPayment() {
		super();
	}

	public boolean isOpen() {
		if (!Strings.isNullOrEmpty(status) && status.equals(StatusDebitForPayment.OPEN.getDescription())) {
			return true;
		}
		return false;
	}
}
