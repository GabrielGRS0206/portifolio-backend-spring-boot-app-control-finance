package io.spring.finance.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.spring.finance.MockUtils;
import io.spring.finance.domain.model.DebitForPayment;
import io.spring.finance.domain.model.Payment;
import io.spring.finance.domain.repository.PaymentRepository;
import io.spring.finance.domain.service.validation.PaymentValidation;

class PaymentServiceTest {

	@Mock
	private PaymentRepository repository;

	@Mock
	private DebitForPaymentService debitForPaymentService;

	@Mock
	private PaymentValidation validation;

	@InjectMocks
	private PaymentService service;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Salva um novo pagamento")
	void saveTest() {

		Payment entity = new Payment();
		entity.setDebitForPayment(new DebitForPayment(MockUtils.getIdOne()));
		when(debitForPaymentService.findById(MockUtils.getIdOne())).thenReturn(mock(DebitForPayment.class));

		service.save(entity);
		verify(debitForPaymentService, times(1)).findById(any());
		verify(repository, times(1)).save(any());

		assertNotNull(entity);
	}

	@Test
	@DisplayName("Salva um novo pagamento")
	void updateTest() {

		Payment entity = new Payment();
		entity.setDebitForPayment(new DebitForPayment(1l));
		when(debitForPaymentService.findById(MockUtils.getIdOne())).thenReturn(mock(DebitForPayment.class));

		service.update(entity, MockUtils.getIdOne());
		verify(repository, times(1)).save(any());

		assertNotNull(entity);
	}

	@Test
	@DisplayName("Busca por id")
	void findByIdTest() {

		Optional<Payment> optional = Optional.ofNullable(new Payment());
		when(repository.findById(MockUtils.getIdOne())).thenReturn(optional);

		Payment payment = service.findById(MockUtils.getIdOne());

		assertNotNull(payment);
	}

	@Test
	@DisplayName("Deleta por id")
	void deletePorIdTest() {

		DebitForPayment debit = new DebitForPayment();
		Optional<Payment> entity = Optional.ofNullable(new Payment());
		entity.get().setDebitForPayment(new DebitForPayment(MockUtils.getIdOne()));

		when(debitForPaymentService.findById(any())).thenReturn(debit);
		when(repository.findById(any())).thenReturn(entity);

		service.delete(MockUtils.getIdOne());

		verify(validation, times(1)).delete(any());
		verify(debitForPaymentService, times(1)).update(any(), any());
		verify(debitForPaymentService, times(1)).findById(any());
	}

	@Test
	@DisplayName("Verifica se existe por id")
	void verifyForIdTest() {
		when(repository.existsById(any())).thenReturn(true);
		assertEquals(true,service.existsById(MockUtils.getIdOne()));
	}
}
