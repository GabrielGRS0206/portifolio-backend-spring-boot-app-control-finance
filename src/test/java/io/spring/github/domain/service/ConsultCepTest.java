/**
 * 
 */
package io.spring.github.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import io.spring.github.domain.exception.business.BusinessException;
import io.spring.github.domain.model.CepResponse;

class ConsultCepTest {

	private static final String CEP = "88904326";

	@InjectMocks
	private ConsultCep consultCep;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test method for
	 * {@link io.spring.github.domain.service.ConsultCep#searchCEP(java.lang.String)}.
	 */
	@Test
	final void testSearchCEP() {
		CepResponse response = consultCep.searchCEP(CEP);
		assertNotNull(response, "response is null");
	}

	@Test
	final void testSearchCEPThrowsException() {
		BusinessException runtimeException = assertThrows(BusinessException.class, () -> consultCep.searchCEP(null));
		assertEquals("Erro ao consultar cep", runtimeException.getMessage());
	}
}
