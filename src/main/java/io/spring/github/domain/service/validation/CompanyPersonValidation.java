package io.spring.github.domain.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import io.spring.github.domain.exception.business.BusinessException;
import io.spring.github.domain.exception.business.EntityInUseException;
import io.spring.github.domain.exception.business.MessageException;
import io.spring.github.domain.repository.CompanyPersonRepository;

@Component
public class CompanyPersonValidation implements Validation {

	@Autowired
	public CompanyPersonRepository repository;

	@Override
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new BusinessException(MessageException.COMPANY_PERSON_NOT_FOUND.getValue(), id);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format("Pessoa de código %d não pode ser removida, pois está em uso", id));
		}
	}

}
