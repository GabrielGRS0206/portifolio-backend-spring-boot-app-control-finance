package io.spring.finance.api.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import io.spring.finance.api.mapper.PaymentMapper;
import io.spring.finance.api.mapper.dto.request.PaymentRequestDTO;
import io.spring.finance.api.mapper.dto.response.PaymentResponseDTO;
import io.spring.finance.domain.model.Payment;
import io.spring.finance.domain.service.PaymentService;

class PaymentV1ControllerTest {

	@Mock
	private PaymentService service;

	@Mock
	private PaymentMapper mapper;

	@InjectMocks
	private PaymentV1Controller controller;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Salva pagamento")
	void saveTest() {

		when(service.save(any())).thenReturn(mock(Payment.class));
		when(mapper.dtoToEntity(any())).thenReturn(mock(Payment.class));
		when(mapper.entityToDto(any())).thenReturn(mock(PaymentResponseDTO.class));

		ResponseEntity<Object> response = controller.save(mock(PaymentRequestDTO.class));

		verify(mapper, times(1)).dtoToEntity(any());
		verify(mapper, times(1)).entityToDto(any());
		verify(service, times(1)).save(any());
		assertNotNull(response, "response is null");
	}

	@Test
	@DisplayName("Deleta pagamento")
	void deleteTest() {
		controller.delete(1l);
		verify(service,times(1)).delete(any());
	}
}
