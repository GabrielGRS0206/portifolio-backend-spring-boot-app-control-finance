package io.spring.finance.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.spring.finance.domain.exception.model_exception.CompanyPersonNotFoundException;
import io.spring.finance.domain.model.CompanyPerson;
import io.spring.finance.domain.model.Contact;
import io.spring.finance.domain.repository.CompanyPersonRepository;
import io.spring.finance.domain.service.validation.CompanyPersonValidation;
import io.spring.finance.domain.utils.UtilsEmun;

class CompanyPersonServiceTest {

	@Mock
	private CompanyPersonRepository repository;

	@Mock
	private ContactService contactService;

	@Mock
	private CompanyPersonValidation validation;

	@InjectMocks
	private CompanyPersonService service;

	private static final String CPF = "11122233399";

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Salva uma nova pessoa ou empresa")
	void saveTest() {

		CompanyPerson entity = companyPerson();
		when(repository.save(any())).thenReturn(entity);

		service.save(entity);

		verify(repository, times(1)).save(entity);
		assertEquals(UtilsEmun.THREE.getIntValue(), entity.getContacts().size());
	}

	private CompanyPerson companyPerson() {
		var entity = new CompanyPerson();
		entity.setDocument(CPF);
		entity.setContacts(contacts());
		return entity;
	}

	private List<Contact> contacts() {
		List<Contact> list = new ArrayList<Contact>();
		list.add(new Contact());
		list.add(new Contact());
		list.add(new Contact());
		return list;
	}

	@Test
	@DisplayName("Atualiza pessoa ou empresa")
	void updateTest() {
		CompanyPerson entity = companyPerson();
		service.update(entity, 1l);
		verify(repository, times(1)).save(entity);
	}

	@Test
	@DisplayName("Busca uma lista de pessoa ou empresa")
	void findAll() {

		when(repository.findAll()).thenReturn(companyPersons());

		List<CompanyPerson> list = service.findAll();

		assertNotNull(list, "list is null");
		assertEquals(UtilsEmun.THREE.getIntValue(), list.size());
	}

	private List<CompanyPerson> companyPersons() {
		List<CompanyPerson> list = new ArrayList<CompanyPerson>();
		list.add(companyPerson());
		list.add(companyPerson());
		list.add(companyPerson());
		return list;
	}

	@Test
	@DisplayName("Consulta uma pessoa existe por id")
	void exitisByIdTest() {

		Optional<CompanyPerson> optional = Optional.ofNullable(new CompanyPerson());
		optional.get().setId(1l);
		when(repository.findById(1l)).thenReturn(optional);

		CompanyPerson entity = service.findById(1l);

		assertNotNull(entity);
		assertEquals(entity.getId(), 1l);
	}

	@Test
	@DisplayName("Verifica se uma pessoa existe por id")
	void searchTest() {

		when(repository.existsById(1l)).thenReturn(true);
		boolean exists = service.existsById(1l);

		assertEquals(true, exists);
	}

	@Test
	@DisplayName("Verifica se uma pessoa existe por um documento")
	void findByDocumentTest() {

		Optional<CompanyPerson> optional = Optional.ofNullable(new CompanyPerson());
		when(repository.findByDocument(CPF)).thenReturn(optional);
		CompanyPerson entity = service.findByDocument(CPF);

		assertNotNull(entity);
		verify(repository, times(1)).findByDocument(any());
	}

}
