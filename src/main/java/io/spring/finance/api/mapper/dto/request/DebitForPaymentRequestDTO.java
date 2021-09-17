package io.spring.finance.api.mapper.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DebitForPaymentRequestDTO {

	private BigDecimal value;
	private LocalDate dueDate;
	private LocalDate emission;
	private Long idCompany;
	private String description;
	private String observation;
	private String parcel;
	
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
	public Long getIdCompany() {
		return idCompany;
	}
	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
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
