package io.spring.finance.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.spring.finance.MockUtils;
import io.spring.finance.api.mapper.dto.request.CompanyPersonRequestDTO;
import io.spring.finance.api.mapper.dto.request.ContactRequestDTO;
import io.spring.finance.api.mapper.dto.response.CompanyPersonResponseDTO;
import io.spring.finance.domain.model.CompanyPerson;
import io.spring.finance.domain.model.Contact;
import io.spring.finance.domain.utils.Utilities;
import io.spring.finance.domain.utils.UtilsEmun;

class CompanyPersonMapperTest {

	@Mock
	private ContactMapper contactMapper;

	@InjectMocks
	private CompanyPersonMapper mapper;

	private static final String PESSOA_FISICA = "F";

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Dto para entidade")
	void mapperDtoToEntityTest() {

		CompanyPersonRequestDTO request = new CompanyPersonRequestDTO();
		request.setContacts(Arrays.asList(new ContactRequestDTO()));
		request.setName(Utilities.replic("A", 20));
		request.setCep(UtilsEmun.VALUE_EMPTY.getValue());
		request.setCity(UtilsEmun.VALUE_EMPTY.getValue());
		request.setDistrict(UtilsEmun.VALUE_EMPTY.getValue());
		request.setStreet(UtilsEmun.VALUE_EMPTY.getValue());
		request.setDocument(Utilities.replic(UtilsEmun.ONE.getValue(), 11));
		request.setTypePerson(PESSOA_FISICA);

		CompanyPerson entity = mapper.dtoToEntity(request);

		assertNotNull(entity, "entity is null");
		assertEquals(request.getContacts().size(), UtilsEmun.ONE.getIntValue());
		assertNotNull(request.getContacts());
		assertEquals(entity.getDocument(), Utilities.replic(UtilsEmun.ONE.getValue(), 11));
		assertEquals(entity.getName(), Utilities.replic("A", 20));
		assertEquals(entity.getTypePerson(), PESSOA_FISICA);
		assertEquals(entity.getCep(), UtilsEmun.VALUE_EMPTY.getValue());
		assertEquals(entity.getCity(), UtilsEmun.VALUE_EMPTY.getValue());
		assertEquals(entity.getStreet(), UtilsEmun.VALUE_EMPTY.getValue());
		assertEquals(entity.getDistrict(), UtilsEmun.VALUE_EMPTY.getValue());
	}

	@Test
	@DisplayName("Entidade para Dto")
	void mapperEntityToDtoTest() {

		CompanyPerson entity = new CompanyPerson();
		entity.setContacts(Arrays.asList(new Contact()));
		entity.setId(1l);
		entity.setName(UtilsEmun.VALUE_EMPTY.getValue());
		entity.setDocument(UtilsEmun.VALUE_EMPTY.getValue());
		entity.setTypePerson(UtilsEmun.VALUE_EMPTY.getValue());
		entity.setCep(UtilsEmun.VALUE_EMPTY.getValue());
		entity.setDistrict(UtilsEmun.VALUE_EMPTY.getValue());
		entity.setStreet(UtilsEmun.VALUE_EMPTY.getValue());
		entity.setCity(UtilsEmun.VALUE_EMPTY.getValue());

		CompanyPersonResponseDTO response = mapper.entityToDto(entity);

		assertNotNull(response, "response is null");
		assertEquals(response.getContacts().size(), UtilsEmun.ONE.getIntValue());
		assertNotNull(response.getContacts());
		assertEquals(response.getId(), MockUtils.getIdOne());
		assertEquals(response.getName(), UtilsEmun.VALUE_EMPTY.getValue());
		assertEquals(response.getDocument(), UtilsEmun.VALUE_EMPTY.getValue());
		assertEquals(response.getCep(), UtilsEmun.VALUE_EMPTY.getValue());
		assertEquals(response.getDistrict(), UtilsEmun.VALUE_EMPTY.getValue());
		assertEquals(response.getStreet(), UtilsEmun.VALUE_EMPTY.getValue());
		assertEquals(response.getCity(), UtilsEmun.VALUE_EMPTY.getValue());
		assertEquals(response.getTypePerson(), UtilsEmun.VALUE_EMPTY.getValue());
	}
}
