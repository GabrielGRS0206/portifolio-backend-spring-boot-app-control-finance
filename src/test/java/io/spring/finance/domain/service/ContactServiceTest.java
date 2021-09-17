package io.spring.finance.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.spring.finance.domain.model.Contact;
import io.spring.finance.domain.repository.ContactRepository;
import io.spring.finance.domain.utils.UtilsEmun;

class ContactServiceTest {

	@Mock
	private ContactRepository repository;

	@InjectMocks
	private ContactService service;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Salva um novo contato")
	void saveTest() {

		Contact entity = mock(Contact.class);
		when(repository.save(any())).thenReturn(entity);

		service.save(entity);

		verify(repository, times(1)).save(entity);
	}

	private List<Contact> contacts() {
		List<Contact> list = new ArrayList<Contact>();
		list.add(new Contact());
		list.add(new Contact());
		list.add(new Contact());
		return list;
	}

	@Test
	@DisplayName("Atualiza contato")
	void updateTest() {
		Contact entity = mock(Contact.class);
		service.update(entity, 1l);
		verify(repository, times(1)).save(entity);
	}

	@Test
	@DisplayName("Busca uma lista de contatos")
	void findAll() {

		when(repository.findAll()).thenReturn(contacts());

		List<Contact> list = service.findAll();

		assertNotNull(list, "list is null");
		assertEquals(list.size(), UtilsEmun.THREE.getIntValue());
	}

	@Test
	@DisplayName("Consulta um contato por id")
	void exitisByIdTest() {

		Optional<Contact> optional = Optional.ofNullable(new Contact());
		optional.get().setId(1l);
		when(repository.findById(1l)).thenReturn(optional);

		Contact entity = service.findById(1l);

		assertNotNull(entity);
		assertEquals(entity.getId(), 1l);
	}

	@Test
	@DisplayName("Verifica se uma contato existe por id")
	void searchTest() {

		when(repository.existsById(1l)).thenReturn(true);
		boolean exists = service.existsById(1l);

		assertEquals(exists, true);
	}

	@Test
	@DisplayName("Delete por id")
	void deleteTest() {
		service.delete(1l);
		verify(repository, times(1)).deleteById(1l);
	}
}
