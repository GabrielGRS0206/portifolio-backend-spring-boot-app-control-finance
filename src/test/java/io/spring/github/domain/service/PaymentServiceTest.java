package io.spring.github.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.spring.github.domain.model.CompanyPerson;
import io.spring.github.domain.model.Payment;
import io.spring.github.domain.repository.PaymentRepository;
import io.spring.github.domain.service.CompanyPersonService;
import io.spring.github.domain.service.PaymentService;
import io.spring.github.domain.utils.UtilsEmun;

class PaymentServiceTest {

	@Mock
	private PaymentRepository repository;

	@Mock
	private CompanyPersonService companyPersonService;

	@InjectMocks
	private PaymentService service;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Salva conta a pagar")
	void saveTest() {

		when(companyPersonService.existsById(1l)).thenReturn(true);

		Payment entity = new Payment();
		entity.setCompany(new CompanyPerson(1l));

		service.save(entity);

		verify(companyPersonService, times(1)).existsById(any());
		verify(repository, times(1)).save(any());
		assertNotNull(entity, "entity is null");
	}

	@Test
	@DisplayName("Atualiza conta a pagar")
	void updateTest() {

		when(companyPersonService.existsById(1l)).thenReturn(true);

		Payment entity = new Payment();
		entity.setCompany(new CompanyPerson(1l));

		service.update(entity, 1l);

		verify(companyPersonService, times(1)).existsById(any());
		verify(repository, times(1)).save(any());
		assertNotNull(entity, "entity is null");
	}

	@Test
	@DisplayName("Lista de contas a pagar")
	void findAllTest() {

		when(repository.findAll()).thenReturn(list());

		List<Payment> list = service.findAll();

		assertNotNull(list, "list is null");
		assertEquals(list.size(), UtilsEmun.THREE.getIntValue());
		verify(repository, times(1)).findAll();
	}

	@Test
	@DisplayName("Salva lista de contas a pagar")
	void saveListTest() {

		when(companyPersonService.existsById(any())).thenReturn(true);
		when(repository.saveAll(any())).thenReturn(list());

		List<Payment> list = service.saveList(list());

		assertNotNull(list, "list is null");
		assertEquals(list.size(), UtilsEmun.THREE.getIntValue());
		verify(repository, times(1)).saveAll(any());
	}

	private List<Payment> list() {
		List<Payment> list = new ArrayList<>();

		Payment entity = new Payment();
		entity.setCompany(new CompanyPerson(1l));
		list.add(entity);
		list.add(entity);
		list.add(entity);
		return list;
	}
}
