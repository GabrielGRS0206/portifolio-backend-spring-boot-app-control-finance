package io.spring.github.domain.service.validation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import io.spring.github.domain.exception.business.BusinessException;
import io.spring.github.domain.exception.business.EntityInUseException;
import io.spring.github.domain.repository.ReceiptPaymentRepository;

class PaymentValidationTest {

	@Mock
	private ReceiptPaymentRepository repository;

	@InjectMocks
	private PaymentValidation validation;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testValidation() {
		validation.delete(any());
		verify(repository, times(1)).deleteById(any());
	}
	@Test
	void testValidationThrowsExceptionEmptyResult() {
		Mockito.doThrow(new EmptyResultDataAccessException(0)).when(repository).deleteById(1l);
		
		Assertions.assertThrows(BusinessException.class, () -> {
			validation.delete(1l);
		});
		verify(repository, times(1)).deleteById(1l);
	}
}
