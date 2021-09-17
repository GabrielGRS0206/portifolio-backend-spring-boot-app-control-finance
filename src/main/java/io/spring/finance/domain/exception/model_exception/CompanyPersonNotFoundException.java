package io.spring.finance.domain.exception.model_exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.spring.finance.domain.exception.business.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CompanyPersonNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	private static final String MSG_COMPANY_PERSON_NOT_FOUND = "Pessoa empresa com código %d não encontrada";

	public CompanyPersonNotFoundException(String mensagem) {
		super(mensagem);
	}

	public CompanyPersonNotFoundException(Long id) {
		this(String.format(MSG_COMPANY_PERSON_NOT_FOUND, id));
	}

}
