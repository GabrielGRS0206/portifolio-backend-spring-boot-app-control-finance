package io.spring.finance.domain.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import io.spring.finance.domain.exception.business.EntityInUseException;
import io.spring.finance.domain.exception.model_exception.CompanyPersonNotFoundException;
import io.spring.finance.domain.repository.CompanyPersonRepository;

@Component
public class CompanyPersonValidation implements Validation {

	@Autowired
	public CompanyPersonRepository repository;

	@Override
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new CompanyPersonNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format("Pessoa empresa de código %d não pode ser removida, pois está em uso", id));
		}
	}

}
