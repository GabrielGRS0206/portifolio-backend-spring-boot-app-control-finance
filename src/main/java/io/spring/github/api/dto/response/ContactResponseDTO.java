package io.spring.github.api.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
public class ContactResponseDTO {

	private Long id;
	private Long idCompanyPerson;
	private String contactDescription;
	private String detail;
	private String typeContact;
}
