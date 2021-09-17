package io.spring.finance.domain.repository.infrasctruture.repository.debit_for_payment;

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

import io.spring.finance.domain.model.DebitForPayment;
import io.spring.finance.domain.utils.UtilsEmun;

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
				.thenReturn(Arrays.asList(new DebitForPayment()));

		List<DebitForPayment> list = repositoryImpl.findAllDebitForPaymentOpen(1l);
		assertNotNull(list);
	}

}
