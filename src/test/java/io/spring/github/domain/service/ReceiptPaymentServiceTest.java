package io.spring.github.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.spring.github.MockUtils;
import io.spring.github.domain.exception.business.BusinessException;
import io.spring.github.domain.model.Payment;
import io.spring.github.domain.model.ReceiptPayment;
import io.spring.github.domain.repository.ReceiptPaymentRepository;
import io.spring.github.domain.service.validation.PaymentValidation;

class ReceiptPaymentServiceTest {

	@Mock
	private ReceiptPaymentRepository repository;

	@Mock
	private PaymentService debitForPaymentService;

	@Mock
	private PaymentValidation validation;

	@InjectMocks
	private ReceiptPaymentService service;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void saveTest() {

		ReceiptPayment entity = new ReceiptPayment();
		entity.setDebitForPayment(new Payment(MockUtils.getIdOne()));
		when(debitForPaymentService.findById(MockUtils.getIdOne())).thenReturn(mock(Payment.class));

		service.save(entity);
		verify(debitForPaymentService, times(1)).findById(any());
		verify(repository, times(1)).save(any());

		assertNotNull(entity);
	}

	@Test
	void updateTest() {

		ReceiptPayment entity = new ReceiptPayment();
		entity.setDebitForPayment(new Payment(1l));
		when(debitForPaymentService.findById(MockUtils.getIdOne())).thenReturn(mock(Payment.class));

		service.update(entity, MockUtils.getIdOne());
		verify(repository, times(1)).save(any());

		assertNotNull(entity);
	}

	@Test
	void findByIdTest() {

		Optional<ReceiptPayment> optional = Optional.ofNullable(new ReceiptPayment());
		when(repository.findById(MockUtils.getIdOne())).thenReturn(optional);

		ReceiptPayment payment = service.findById(MockUtils.getIdOne());

		assertNotNull(payment);
	}

	@Test
	void findByIdTestThrows() {
		Assertions.assertThrows(BusinessException.class, () ->{
			service.findById(1l);
		});
	}
	
	@Test
	void deletePorIdTest() {

		Payment debit = new Payment();
		Optional<ReceiptPayment> entity = Optional.ofNullable(new ReceiptPayment());
		entity.get().setDebitForPayment(new Payment(MockUtils.getIdOne()));

		when(debitForPaymentService.findById(any())).thenReturn(debit);
		when(repository.findById(any())).thenReturn(entity);

		service.delete(MockUtils.getIdOne());

		verify(validation, times(1)).delete(any());
		verify(debitForPaymentService, times(1)).update(any(), any());
		verify(debitForPaymentService, times(1)).findById(any());
	}

	@Test
	void verifyForIdTest() {
		when(repository.existsById(any())).thenReturn(true);
		assertEquals(true,service.existsById(MockUtils.getIdOne()));
	}
	
	@Test
	void testFindAll() {
		when(repository.findAll()).thenReturn(Arrays.asList(new ReceiptPayment()));
		List<ReceiptPayment> list = service.findAll();
		assertNotNull(list,"list is null");
	}
}
