package io.spring.finance.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.spring.finance.api.mapper.dto.request.ContactRequestDTO;
import io.spring.finance.api.mapper.dto.response.ContactResponseDTO;
import io.spring.finance.domain.model.CompanyPerson;
import io.spring.finance.domain.model.Contact;
import io.spring.finance.domain.utils.UtilsEmun;

class ContactMapperTest {

	private ContactMapper mapper = new ContactMapper();
	private static final String VALUE_EMPTY = UtilsEmun.VALUE_EMPTY.getValue();
	@Test
	@DisplayName("Dto para entidade")
	void mapperDtoToEntityTest() {

		ContactRequestDTO request = new ContactRequestDTO();
		request.setContactDescription(VALUE_EMPTY);
		request.setDetail(VALUE_EMPTY);
		request.setTypeContact(VALUE_EMPTY);
		
		Contact entity = mapper.dtoToEntity(request);
		assertNotNull(entity, "entity is null");
		
		assertEquals(entity.getContactDescription(), VALUE_EMPTY);
		assertEquals(entity.getDetail(), VALUE_EMPTY);
		assertEquals(entity.getTypeContact(), VALUE_EMPTY);
	}

	@Test
	@DisplayName("Entidade para Dto")
	void mapperEntityToDtoTest() {

		Contact entity = new Contact();
		entity.setCompanyPerson(new CompanyPerson(1l));
		entity.setId(1l);
		entity.setContactDescription(VALUE_EMPTY);
		entity.setDetail(VALUE_EMPTY);
		entity.setTypeContact(VALUE_EMPTY);
		ContactResponseDTO response = mapper.entityToDto(entity);
		assertNotNull(response, "response is null");
		assertEquals(response.getId(), 1l);
		assertEquals(response.getIdCompanyPerson(), 1l);
		assertEquals(response.getContactDescription(), VALUE_EMPTY);
		assertEquals(response.getDetail(), VALUE_EMPTY);
		assertEquals(response.getTypeContact(), VALUE_EMPTY);
	}
}
