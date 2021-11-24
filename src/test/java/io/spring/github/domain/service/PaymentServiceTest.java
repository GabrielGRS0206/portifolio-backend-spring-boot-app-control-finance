package io.spring.github.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.spring.github.domain.exception.business.BusinessException;
import io.spring.github.domain.model.CompanyPerson;
import io.spring.github.domain.model.Payment;
import io.spring.github.domain.model.StatusPayment;
import io.spring.github.domain.repository.PaymentRepository;
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
	
	@Test
	void testVerifyCompanyPersonExists() {
		when(repository.existsById(1l)).thenReturn(false);
		
		Assertions.assertThrows(BusinessException.class, () ->{
			service.verifyCompanyPersonExists(new CompanyPerson(1l));
		});
	}
	
	@Test
	void testFindById() {
		when(repository.findById(1l)).thenReturn(Optional.of(new Payment(1l)));
		Payment payment = service.findById(1l);
		assertNotNull(payment, "payment is null");
	}
	
	@Test
	void testFindByIdThrows() {
		when(repository.findById(1l)).thenReturn(Optional.of(new Payment(1l)));
		
		Assertions.assertThrows(BusinessException.class, () ->{
			service.findById(2l);
		});
	}
	
	@Test
	void testListPayments() {
		when(repository.findAllDebitForPaymentOpen(1l)).
				thenReturn(Arrays.asList(new Payment(1l)));
		List<Payment> list = service.listDebitForPaymentOpen(1l);
		assertNotNull(list, "list is null");
	}
	
	@Test
	void testDelete() {
		Payment payment = new Payment();
		payment.setId(1l);
		payment.setStatus(StatusPayment.OPEN.getDescription());
		when(repository.findById(1l)).thenReturn(Optional.of(payment));
		service.delete(1l);
		verify(repository,times(1)).findById(1l);
	}
	
	@Test
	void testDeleteThrows() {
		Payment payment = new Payment();
		payment.setId(1l);
		payment.setStatus(StatusPayment.SETTLED.getDescription());
		when(repository.findById(1l)).thenReturn(Optional.of(payment));
		
		Assertions.assertThrows(BusinessException.class, () ->{
			service.delete(1l);
		});
		verify(repository,times(1)).findById(1l);
	}
	
	@Test
	void testExistsById() {
		boolean retorno = service.existsById(2l);
		assertEquals(retorno, false);
		verify(repository, times(1)).existsById(2l);
	}
}

