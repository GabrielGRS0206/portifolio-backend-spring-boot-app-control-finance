package io.spring.github.domain.model;

public enum StatusDebitForPayment {

	SETTLED("SETTELD"), OPEN("OPEN");

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
