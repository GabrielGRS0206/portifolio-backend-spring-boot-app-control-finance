package io.spring.github.domain.repository.infrasctruture.repository.debit_for_payment;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import io.spring.github.domain.model.Payment;
import io.spring.github.domain.utils.UtilsEmun;

class DebitForPaymentRepositoryImplTest {

	@Mock
	public JdbcTemplate jdbcTemplate;

	@InjectMocks
	private DebitForPaymentRepositoryImpl repositoryImpl;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAllDebitForPaymentOpen() {

		when(jdbcTemplate.query(UtilsEmun.VALUE_EMPTY.getValue(), new DebitForPaymentRowMapper()))
				.thenReturn(Arrays.asList(new Payment()));

		List<Payment> list = repositoryImpl.findAllDebitForPaymentOpen(1l);
		assertNotNull(list);
	}

}
