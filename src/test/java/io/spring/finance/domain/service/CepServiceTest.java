package io.spring.finance.domain.service;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CepServiceTest {

	private CepService service = new CepService();
	private static final String CEP = "8896000";

	@Test
	@DisplayName("Consulta cep")
	void searchCepTest() {

		try {
			CepResponse response = service.searchCep(CEP);
			assertNull(response);
		} catch (Exception e) {
		}
	}

}
