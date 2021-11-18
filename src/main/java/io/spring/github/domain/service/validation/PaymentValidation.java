package io.spring.github.domain.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import io.spring.github.domain.exception.business.BusinessException;
import io.spring.github.domain.exception.business.MessageException;
import io.spring.github.domain.repository.PaymentRepository;

@Component
public class PaymentValidation implements Validation {

	@Autowired
	public PaymentRepository repository;
	
	@Override
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new BusinessException(MessageException.PAYMENT_NOT_FOUND.getValue(),id);
		}
	}
}
