package io.spring.finance.domain.repository.infrasctruture.repository.debit_for_payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.spring.finance.domain.model.DebitForPayment;

@Repository
public class DebitForPaymentRepositoryImpl implements DebitForPaymentRepositoryQueries {

	@Autowired
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<DebitForPayment> findAllDebitForPaymentOpen(long id) {

		String sql = " SELECT c.* FROM debit_payment c " + "WHERE c.id_company = " + id + " AND c.status = 'A' ";

		List<DebitForPayment> debits = jdbcTemplate.query(sql, new DebitForPaymentRowMapper());
		return debits;
	}

}
