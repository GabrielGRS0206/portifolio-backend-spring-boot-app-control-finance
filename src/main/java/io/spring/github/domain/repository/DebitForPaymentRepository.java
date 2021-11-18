package io.spring.github.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.spring.github.domain.model.DebitForPayment;
import io.spring.github.domain.repository.infrasctruture.repository.debit_for_payment.DebitForPaymentRepositoryQueries;

@Repository
public interface DebitForPaymentRepository extends JpaRepository<DebitForPayment, Long>, DebitForPaymentRepositoryQueries{

}
