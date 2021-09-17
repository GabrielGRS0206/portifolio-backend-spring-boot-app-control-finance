package io.spring.finance.api.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import io.spring.finance.api.mapper.CepMapper;
import io.spring.finance.api.mapper.dto.response.CepResponseDTO;
import io.spring.finance.domain.service.CepResponse;
import io.spring.finance.domain.service.CepService;
import io.spring.finance.domain.utils.UtilsEmun;

class CepV1ControllerTest {

	@Mock
	private CepService service;

	@InjectMocks
	private CepV1Controller controller;

	@Mock
	private CepMapper mapper;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void searchCeptest() {

		when(service.searchCep(UtilsEmun.VALUE_EMPTY.getValue())).thenReturn(mock(CepResponse.class));
		when(mapper.mapper(any())).thenReturn(mock(CepResponseDTO.class));

		ResponseEntity<Object> response = controller.searchCep(UtilsEmun.VALUE_EMPTY.getValue());
		assertNotNull(response, "response is null");
	}

}
