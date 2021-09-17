package io.spring.finance.api.mapper;

import org.springframework.stereotype.Component;

import io.spring.finance.api.mapper.dto.request.ContactRequestDTO;
import io.spring.finance.api.mapper.dto.response.ContactResponseDTO;
import io.spring.finance.domain.model.Contact;

@Component
public class ContactMapper {

	public Contact dtoToEntity(ContactRequestDTO e) {
		var contact = new Contact();
		contact.setDetail(e.getDetail());
		contact.setContactDescription(e.getContactDescription());
		contact.setTypeContact(e.getTypeContact());
		return contact;
	}

	public ContactResponseDTO entityToDto(Contact e) {
		var response = new ContactResponseDTO();
		response.setId(e.getId());
		response.setContactDescription(e.getContactDescription());
		response.setDetail(e.getDetail());
		response.setTypeContact(e.getTypeContact());
		response.setIdCompanyPerson(e.getCompanyPerson().getId());
		return response;
	}
}
