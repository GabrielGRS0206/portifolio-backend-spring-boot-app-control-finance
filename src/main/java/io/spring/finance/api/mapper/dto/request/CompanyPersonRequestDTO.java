package io.spring.finance.api.mapper.dto.request;

import java.util.List;

public class CompanyPersonRequestDTO {

	private String name;
	private String document;
	private String typePerson;
	private String cep;
	private String district;
	private String street;
	private String city;
	private List<ContactRequestDTO> contacts;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getTypePerson() {
		return typePerson;
	}

	public void setTypePerson(String typePerson) {
		this.typePerson = typePerson;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<ContactRequestDTO> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactRequestDTO> contacts) {
		this.contacts = contacts;
	}

}
