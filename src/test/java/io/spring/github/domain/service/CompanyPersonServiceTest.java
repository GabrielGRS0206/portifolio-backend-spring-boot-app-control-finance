package io.spring.github.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.spring.github.domain.exception.business.BusinessException;
import io.spring.github.domain.model.CompanyPerson;
import io.spring.github.domain.model.Contact;
import io.spring.github.domain.repository.CompanyPersonRepository;
import io.spring.github.domain.service.validation.CompanyPersonValidation;
import io.spring.github.domain.utils.UtilsEmun;

class CompanyPersonServiceTest {

	private static Long ID = 1l;
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
	void updateTest() {
		CompanyPerson entity = companyPerson();
		service.update(entity, ID);
		verify(repository, times(1)).save(entity);
	}

	@Test
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
	void exitisByIdTest() {

		Optional<CompanyPerson> optional = Optional.ofNullable(new CompanyPerson());
		optional.get().setId(1l);
		when(repository.findById(1l)).thenReturn(optional);

		CompanyPerson entity = service.findById(1l);

		assertNotNull(entity);
		assertEquals(entity.getId(), ID);
	}

	@Test
	void searchTest() {

		when(repository.existsById(ID)).thenReturn(true);
		boolean exists = service.existsById(ID);

		assertEquals(true, exists);
	}

	@Test
	void findByDocumentTest() {

		Optional<CompanyPerson> optional = Optional.ofNullable(new CompanyPerson());
		when(repository.findByDocument(CPF)).thenReturn(optional);
		CompanyPerson entity = service.findByDocument(CPF);

		assertNotNull(entity);
		verify(repository, times(1)).findByDocument(any());
	}
	
	@Test
	void testDelete() {
		service.delete(1l);
		verify(repository, times(0)).deleteById(1l);
	}
}
