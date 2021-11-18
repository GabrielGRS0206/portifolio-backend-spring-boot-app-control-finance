/**
 * 
 */
package io.spring.github.domain.repository.infrasctruture.repository.debit_for_payment;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import io.spring.github.domain.model.Payment;
import io.spring.github.domain.repository.infrasctruture.repository.debit_for_payment.DebitForPaymentRowMapper;

class DebitForPaymentRowMapperTest {

	private DebitForPaymentRowMapper rowMapper = new DebitForPaymentRowMapper();

	@Test
	void testRowMapper() throws SQLException {
		var result = mock(ResultSet.class);
		Payment debit = rowMapper.mapRow(result, 0);
		assertNotNull(debit);
	}

}
