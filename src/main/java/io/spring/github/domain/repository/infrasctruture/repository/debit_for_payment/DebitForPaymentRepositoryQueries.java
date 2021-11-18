package io.spring.github.domain.repository.infrasctruture.repository.debit_for_payment;

import java.util.List;

import io.spring.github.domain.model.Payment;

public interface DebitForPaymentRepositoryQueries {
	
	List<Payment> findAllDebitForPaymentOpen(long id);
}
