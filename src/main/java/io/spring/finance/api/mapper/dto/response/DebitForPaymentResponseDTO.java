package io.spring.finance.api.mapper.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DebitForPaymentResponseDTO {

	private Long id;
	private BigDecimal value;
	private LocalDate dueDate;
	private LocalDate emission;
	private Long idCompanyPerson;
	private String status;
	private String description;
	private String observation;
	private String parcel;

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

	public Long getIdCompanyPerson() {
		return idCompanyPerson;
	}

	public void setIdCompanyPerson(Long idCompanyPerson) {
		this.idCompanyPerson = idCompanyPerson;
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

	public String getParcel() {
		return parcel;
	}

	public void setParcel(String parcel) {
		this.parcel = parcel;
	}

}
