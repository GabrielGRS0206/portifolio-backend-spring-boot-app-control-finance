package io.spring.github.api.mapper.dto.response;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
public class CompanyPersonResponseDTO {

	private Long id;
	private String name;
	private String document;
	private String typePerson;
	private List<ContactResponseDTO> contacts;
	private String cep;
	private String district;
	private String street;
	private String city;
}
