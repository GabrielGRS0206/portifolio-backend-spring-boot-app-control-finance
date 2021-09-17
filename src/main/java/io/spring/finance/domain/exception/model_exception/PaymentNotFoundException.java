package io.spring.finance.domain.exception.model_exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.spring.finance.domain.exception.business.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PaymentNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	private static final String MSG_PAYMENT_NOT_FOUND = "Pagamento com código %d não encontrado";

	public PaymentNotFoundException(String mensagem) {
		super(mensagem);
	}

	public PaymentNotFoundException(Long id) {
		this(String.format(MSG_PAYMENT_NOT_FOUND, id));
	}

}
