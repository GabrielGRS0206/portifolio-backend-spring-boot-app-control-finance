package io.spring.finance.domain.exception.model_exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.spring.finance.domain.exception.business.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	private static final String MSG_CONTACT_NOT_FOUND = "Contato com código %d não encontrado";

	public ContactNotFoundException(String mensagem) {
		super(mensagem);
	}

	public ContactNotFoundException(Long id) {
		this(String.format(MSG_CONTACT_NOT_FOUND, id));
	}

}
