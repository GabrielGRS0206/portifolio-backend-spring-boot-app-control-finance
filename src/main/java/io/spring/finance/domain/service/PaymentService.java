package io.spring.finance.domain.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.finance.domain.exception.model_exception.PaymentNotFoundException;
import io.spring.finance.domain.model.DebitForPayment;
import io.spring.finance.domain.model.Payment;
import io.spring.finance.domain.model.StatusDebitForPayment;
import io.spring.finance.domain.repository.PaymentRepository;
import io.spring.finance.domain.service.validation.PaymentValidation;

@Service
public class PaymentService implements Operations<Payment> {

	@Autowired
	private PaymentRepository repository;

	@Autowired
	private PaymentValidation validation;

	@Autowired
	private DebitForPaymentService debitForPaymentService;

	@Override
	public Payment save(Payment entity) {
		Objects.requireNonNull(entity, "Objeto nao pode ser null");

		DebitForPayment debitForPayment = debitForPaymentService.findById(entity.getDebitForPayment().getId());
		debitForPayment.setStatus(StatusDebitForPayment.SETTLED.getDescription());
		debitForPaymentService.update(debitForPayment, debitForPayment.getId());

		return repository.save(entity);
	}

	@Override
	public Payment update(Payment entity, Long id) {
		entity.setId(id);
		return repository.save(entity);
	}

	@Override
	public Payment findById(Long id) {
		Optional<Payment> entity = repository.findById(id);

		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new PaymentNotFoundException(id);
		}
	}

	@Override
	public void delete(Long id) {
		
		Payment entity = findById(id);
		validation.delete(id);

		DebitForPayment debitForPayment = debitForPaymentService.findById(entity.getDebitForPayment().getId());
		debitForPayment.setStatus(StatusDebitForPayment.OPEN.getDescription());
		debitForPaymentService.update(debitForPayment, debitForPayment.getId());
	}

	@Override
	public List<Payment> findAll() {
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}
}
