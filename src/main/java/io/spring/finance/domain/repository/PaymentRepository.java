package io.spring.finance.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.spring.finance.domain.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
