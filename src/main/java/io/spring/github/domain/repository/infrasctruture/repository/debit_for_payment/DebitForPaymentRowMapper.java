package io.spring.github.domain.repository.infrasctruture.repository.debit_for_payment;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.spring.github.domain.model.CompanyPerson;
import io.spring.github.domain.model.DebitForPayment;
import io.spring.github.domain.utils.Utilities;

public class DebitForPaymentRowMapper implements RowMapper<DebitForPayment> {

	@Override
	public DebitForPayment mapRow(ResultSet rs, int rowNum) throws SQLException {
		var entity = new DebitForPayment();
		entity.setId(rs.getLong("id"));
		entity.setCompany(new CompanyPerson(rs.getLong("id_company")));
		entity.setDescription(rs.getString("description"));
		entity.setObservation(rs.getString("observation"));
		entity.setDueDate(Utilities.removeNull(rs.getDate("due_date")).toLocalDate());
		entity.setEmission(Utilities.removeNull(rs.getDate("emission")).toLocalDate());
		entity.setValue(rs.getBigDecimal("value"));
		entity.setStatus(rs.getString("status"));
		entity.setParcel(rs.getString("parcel"));
		return entity;
	}
}
