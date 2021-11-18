package io.spring.github.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.spring.github.api.mapper.ContactMapper;
import io.spring.github.api.mapper.dto.request.ContactRequestDTO;
import io.spring.github.api.mapper.dto.response.ContactResponseDTO;
import io.spring.github.domain.model.CompanyPerson;
import io.spring.github.domain.model.Contact;
import io.spring.github.domain.utils.UtilsEmun;

class ContactMapperTest {

	private static long ID = 1L;
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

		assertEquals(VALUE_EMPTY, entity.getContactDescription());
		assertEquals(VALUE_EMPTY, entity.getDetail());
		assertEquals(VALUE_EMPTY, entity.getTypeContact());
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
		assertEquals(ID, response.getId());
		assertEquals(ID, response.getIdCompanyPerson());
		assertEquals(VALUE_EMPTY, response.getContactDescription());
		assertEquals(VALUE_EMPTY, response.getDetail());
		assertEquals(VALUE_EMPTY, response.getTypeContact());
	}
}
