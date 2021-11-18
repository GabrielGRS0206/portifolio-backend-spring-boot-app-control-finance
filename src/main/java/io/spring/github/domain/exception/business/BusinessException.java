package io.spring.github.domain.exception.business;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String msg, Long id) {
		this(String.format(msg, id));
	}

	public BusinessException(String msg, String id) {
		this(String.format(msg, id));
	}
	
	public BusinessException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
