package io.spring.finance.domain.exception.model_exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.spring.finance.domain.exception.business.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DebitForPaymentNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	private static final String MSG_DEBIT_FOR_PAYMENT_NOT_FOUND = "Conta para pagamento com código %d não encontrada";

	public DebitForPaymentNotFoundException(String mensagem) {
		super(mensagem);
	}

	public DebitForPaymentNotFoundException(Long id) {
		this(String.format(MSG_DEBIT_FOR_PAYMENT_NOT_FOUND, id));
	}

}
