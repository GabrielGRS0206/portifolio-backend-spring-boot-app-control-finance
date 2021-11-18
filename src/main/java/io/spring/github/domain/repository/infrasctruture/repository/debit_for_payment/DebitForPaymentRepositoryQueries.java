package io.spring.github.domain.repository.infrasctruture.repository.debit_for_payment;

import java.util.List;

import io.spring.github.domain.model.DebitForPayment;

public interface DebitForPaymentRepositoryQueries {
	
	List<DebitForPayment> findAllDebitForPaymentOpen(long id);
}
