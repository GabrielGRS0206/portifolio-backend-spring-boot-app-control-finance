package io.spring.finance.domain.repository.infrasctruture.repository.debit_for_payment;

import java.util.List;

import io.spring.finance.domain.model.DebitForPayment;

public interface DebitForPaymentRepositoryQueries {
	
	List<DebitForPayment> findAllDebitForPaymentOpen(long id);
}
