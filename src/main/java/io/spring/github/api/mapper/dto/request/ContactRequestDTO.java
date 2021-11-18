package io.spring.github.api.mapper.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
public class ContactRequestDTO {

	private String contactDescription;
	private String detail;
	private String typeContact;
}
