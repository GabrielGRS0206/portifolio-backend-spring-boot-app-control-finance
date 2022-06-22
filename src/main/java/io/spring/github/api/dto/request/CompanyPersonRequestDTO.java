package io.spring.github.api.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class CompanyPersonRequestDTO {

	private String name;
	private String document;
	private String typePerson;
	private String cep;
	private String district;
	private String street;
	private String city;
	private List<ContactRequestDTO> contacts;
}
