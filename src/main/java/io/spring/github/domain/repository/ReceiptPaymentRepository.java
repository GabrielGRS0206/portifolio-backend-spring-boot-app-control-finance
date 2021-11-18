package io.spring.github.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.spring.github.domain.model.ReceiptPayment;

@Repository
public interface ReceiptPaymentRepository extends JpaRepository<ReceiptPayment, Long> {

}
