package io.spring.github.domain.repository.infrasctruture.repository.debit_for_payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.spring.github.domain.model.Payment;

@Repository
public class DebitForPaymentRepositoryImpl implements DebitForPaymentRepositoryQueries {

	@Autowired
	public JdbcTemplate jdbcTemplate;

	@Override
	public List<Payment> findAllDebitForPaymentOpen(long id) {

		String sql = " SELECT c.* FROM debit_payment c " + "WHERE c.id_company = " + id + " AND c.status = 'A' ";
		return jdbcTemplate.query(sql, new DebitForPaymentRowMapper());
	}

}
