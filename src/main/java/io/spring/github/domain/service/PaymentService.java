package io.spring.github.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.github.domain.exception.business.BusinessException;
import io.spring.github.domain.exception.business.MessageException;
import io.spring.github.domain.model.DebitForPayment;
import io.spring.github.domain.model.Payment;
import io.spring.github.domain.model.StatusDebitForPayment;
import io.spring.github.domain.repository.PaymentRepository;
import io.spring.github.domain.service.validation.PaymentValidation;

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
		Objects.requireNonNull(entity, MessageException.OBJECT_NOT_NULL.getValue());

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
			throw new BusinessException(MessageException.PAYMENT_NOT_FOUND.getValue(),id);
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
		return new ArrayList<>();
	}

	@Override
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}
}
