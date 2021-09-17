package io.spring.finance.domain.exception.model_exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.spring.finance.domain.exception.business.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CepNotFound extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	private static final String MSG_ERROR_SEARCH_CEP = "Erro ao consultar cep %d";

	public CepNotFound(String mensagem) {
		super(mensagem);
	}

	public CepNotFound(Long id) {
		this(String.format(MSG_ERROR_SEARCH_CEP, id));
	}

}
