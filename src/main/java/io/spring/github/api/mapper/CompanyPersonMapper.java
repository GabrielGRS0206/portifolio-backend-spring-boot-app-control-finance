package io.spring.github.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.spring.github.api.dto.request.CompanyPersonRequestDTO;
import io.spring.github.api.dto.request.ContactRequestDTO;
import io.spring.github.api.dto.response.CompanyPersonResponseDTO;
import io.spring.github.api.dto.response.ContactResponseDTO;
import io.spring.github.domain.model.CompanyPerson;
import io.spring.github.domain.model.Contact;

@Component
public class CompanyPersonMapper extends ValidConstrains<CompanyPerson>{

	@Autowired
	private ContactMapper mapperContact;

	public CompanyPerson dtoToEntity(CompanyPersonRequestDTO request) {
		var entity = new CompanyPerson();
		entity.setDocument(request.getDocument());
		entity.setName(request.getName());
		entity.setTypePerson(request.getTypePerson());
		entity.setContacts(dtoToEntityContacts(request.getContacts()));
		entity.setCep(request.getCep());
		entity.setCity(request.getCity());
		entity.setStreet(request.getStreet());
		entity.setDistrict(request.getDistrict());
		isValid(entity);
		return entity;
	}

	public CompanyPersonResponseDTO entityToDto(CompanyPerson entity) {
		var response = new CompanyPersonResponseDTO();
		response.setDocument(entity.getDocument());
		response.setId(entity.getId());
		response.setName(entity.getName());
		response.setTypePerson(entity.getTypePerson());
		response.setContacts(entityToDtoContacts(entity.getContacts()));
		response.setCep(entity.getCep());
		response.setDistrict(entity.getDistrict());
		response.setCity(entity.getCity());
		response.setStreet(entity.getStreet());
		return response;
	}

	private List<Contact> dtoToEntityContacts(List<ContactRequestDTO> contacts) {
		return contacts.stream().map(e -> mapperContact.dtoToEntity(e)).collect(Collectors.toList());
	}

	private List<ContactResponseDTO> entityToDtoContacts(List<Contact> contacts) {
		return contacts.stream().map(e -> mapperContact.entityToDto(e)).collect(Collectors.toList());
	}
}
