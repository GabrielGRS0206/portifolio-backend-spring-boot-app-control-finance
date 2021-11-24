package io.spring.github.domain.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.spring.github.domain.model.CepResponse;

class CepServiceTest {

	@Mock
	private ConsultCep cepClient;

	@InjectMocks
	private CepService service;

	private static final String CEP = "8896000";

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void searchCepTest() {
		when(cepClient.searchCEP(CEP)).thenReturn(new CepResponse());
		CepResponse response = service.searchCep(CEP);
		assertNotNull(response,"response is null");
	}
}
