package io.spring.github.domain.service.validation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.spring.github.domain.repository.PaymentRepository;
import io.spring.github.domain.service.validation.PaymentValidation;

class PaymentValidationTest {

	@Mock
	private PaymentRepository repository;

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

}
