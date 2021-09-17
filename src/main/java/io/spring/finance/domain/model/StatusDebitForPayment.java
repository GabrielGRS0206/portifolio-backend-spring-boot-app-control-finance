package io.spring.finance.domain.model;

public enum StatusDebitForPayment {

	SETTLED("QUITADA"), OPEN("ABERTA");

	private String description;

	StatusDebitForPayment(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
