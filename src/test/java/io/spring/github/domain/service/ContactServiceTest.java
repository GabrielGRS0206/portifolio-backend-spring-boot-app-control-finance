package io.spring.github.domain.service;

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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.spring.github.MockUtils;
import io.spring.github.domain.exception.business.BusinessException;
import io.spring.github.domain.model.Contact;
import io.spring.github.domain.repository.ContactRepository;
import io.spring.github.domain.service.ContactService;
import io.spring.github.domain.utils.UtilsEmun;

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
		service.update(entity, MockUtils.getIdOne());
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
		optional.get().setId(MockUtils.getIdOne());
		when(repository.findById(MockUtils.getIdOne())).thenReturn(optional);

		Contact entity = service.findById(MockUtils.getIdOne());

		assertNotNull(entity);
		assertEquals(MockUtils.getIdOne(),entity.getId());
	}

	@Test
	@DisplayName("Verifica se uma contato existe por id")
	void searchTest() {

		when(repository.existsById(1l)).thenReturn(true);
		boolean exists = service.existsById(MockUtils.getIdOne());

		assertEquals(true,exists);
	}

	@Test
	@DisplayName("Delete por id")
	void deleteTest() {
		service.delete(1l);
		verify(repository, times(1)).deleteById(MockUtils.getIdOne());
	}

	@Test
	void testFindByThrows() {
		Assertions.assertThrows(BusinessException.class, () ->{
			service.findById(1l);
		});
	}
}
