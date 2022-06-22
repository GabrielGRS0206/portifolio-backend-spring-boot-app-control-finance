package io.spring.github.api.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.spring.github.api.dto.request.CompanyPersonRequestDTO;
import io.spring.github.api.dto.request.ContactRequestDTO;
import io.spring.github.api.dto.response.CompanyPersonResponseDTO;
import io.spring.github.domain.exception.business.DtoInvalidException;
import io.spring.github.domain.model.CompanyPerson;
import io.spring.github.domain.model.Contact;

class CompanyPersonMapperTest {

	@Mock
	private ContactMapper contactMapper;

	@InjectMocks
	private CompanyPersonMapper mapper;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void mapperDtoToEntityTest() {
		CompanyPersonRequestDTO request = new CompanyPersonRequestDTO();
		request.setTypePerson("F");
		request.setName("TESTE");
		request.setDocument("11122255588");
		request.setContacts(Arrays.asList(new ContactRequestDTO()));
		CompanyPerson entity = mapper.dtoToEntity(request);
		assertNotNull(entity, "entity is null");
	}
	
	@Test
	void mapperDtoToEntityTestThrowsException() {
		
		DtoInvalidException exception = assertThrows(DtoInvalidException.class, ()->{
			CompanyPersonRequestDTO request = new CompanyPersonRequestDTO();
			request.setContacts(Arrays.asList(new ContactRequestDTO()));
			mapper.dtoToEntity(request);
		});
		assertNotNull(exception);
	}

	@Test
	void mapperEntityToDtoTest() {
		CompanyPerson entity = new CompanyPerson();
		entity.setContacts(Arrays.asList(new Contact()));
		CompanyPersonResponseDTO response = mapper.entityToDto(entity);
		assertNotNull(response, "response is null");
	}
}
