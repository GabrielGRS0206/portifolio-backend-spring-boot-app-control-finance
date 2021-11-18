package io.spring.github.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.github.domain.exception.business.BusinessException;
import io.spring.github.domain.exception.business.MessageException;
import io.spring.github.domain.model.Payment;
import io.spring.github.domain.model.ReceiptPayment;
import io.spring.github.domain.model.StatusPayment;
import io.spring.github.domain.repository.ReceiptPaymentRepository;
import io.spring.github.domain.service.validation.PaymentValidation;

@Service
public class ReceiptPaymentService implements Operations<ReceiptPayment> {

	@Autowired
	private ReceiptPaymentRepository repository;

	@Autowired
	private PaymentValidation validation;

	@Autowired
	private PaymentService debitForPaymentService;

	@Override
	public ReceiptPayment save(ReceiptPayment entity) {
		Objects.requireNonNull(entity, MessageException.OBJECT_NOT_NULL.getValue());

		Payment debitForPayment = debitForPaymentService.findById(entity.getDebitForPayment().getId());
		debitForPayment.setStatus(StatusPayment.SETTLED.getDescription());
		debitForPaymentService.update(debitForPayment, debitForPayment.getId());

		return repository.save(entity);
	}

	@Override
	public ReceiptPayment update(ReceiptPayment entity, Long id) {
		entity.setId(id);
		return repository.save(entity);
	}

	@Override
	public ReceiptPayment findById(Long id) {
		Optional<ReceiptPayment> entity = repository.findById(id);

		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new BusinessException(MessageException.PAYMENT_NOT_FOUND.getValue(),id);
		}
	}

	@Override
	public void delete(Long id) {
		
		ReceiptPayment entity = findById(id);
		validation.delete(id);

		Payment debitForPayment = debitForPaymentService.findById(entity.getDebitForPayment().getId());
		debitForPayment.setStatus(StatusPayment.OPEN.getDescription());
		debitForPaymentService.update(debitForPayment, debitForPayment.getId());
	}

	@Override
	public List<ReceiptPayment> findAll() {
		return new ArrayList<>();
	}

	@Override
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}
}
