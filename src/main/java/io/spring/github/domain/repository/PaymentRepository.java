package io.spring.github.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.spring.github.domain.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
