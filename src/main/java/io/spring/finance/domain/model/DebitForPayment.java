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

import com.google.common.base.Strings;

@Entity
@Table(name = "debit_payment")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getParcel() {
		return parcel;
	}

	public void setParcel(String parcel) {
		this.parcel = parcel;
	}


	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getEmission() {
		return emission;
	}

	public void setEmission(LocalDate emission) {
		this.emission = emission;
	}

	public CompanyPerson getCompany() {
		return company;
	}

	public void setCompany(CompanyPerson company) {
		this.company = company;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	
}
