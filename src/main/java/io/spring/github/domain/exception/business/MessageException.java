package io.spring.github.domain.exception.business;

public enum MessageException {

	DATA_IMCOMPLET("Dados incompletos"), 
	OBJECT_NOT_NULL("Objeto não pode ser null"),
	COMPANY_PERSON_NOT_FOUND("Pessoa com código %d não encontrada"),
	ERROR_SEARCH_CEP("Erro ao consultar cep %d"),
	CONTACT_NOT_FOUND("Contato com código %d não encontrado"),
	DEBIT_FOR_PAYMENT_NOT_FOUND("Conta para pagamento com código %d não encontrada"),
	PAYMENT_NOT_FOUND("Pagamento com código %d não encontrado"),
	COMPANY_PERSON_EXITS("Pessoa ou empresa com documento %d já adicionado");

	private String value;

	MessageException(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
