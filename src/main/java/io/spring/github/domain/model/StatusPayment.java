package io.spring.github.domain.model;

public enum StatusPayment {

	SETTLED("SETTELD"), OPEN("OPEN");

	private String description;

	StatusPayment(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
