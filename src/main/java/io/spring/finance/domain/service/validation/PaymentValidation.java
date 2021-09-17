package io.spring.finance.domain.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import io.spring.finance.domain.exception.model_exception.PaymentNotFoundException;
import io.spring.finance.domain.repository.PaymentRepository;

@Component
public class PaymentValidation implements Validation {

	@Autowired
	public PaymentRepository repository;
	
	@Override
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new PaymentNotFoundException(id);
		}
	}
}
