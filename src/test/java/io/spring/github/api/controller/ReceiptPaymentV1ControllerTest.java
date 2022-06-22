package io.spring.github.api.controller;

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

import io.spring.github.api.mapper.ReceiptPaymentMapper;
import io.spring.github.api.dto.request.ReceiptPaymentRequestDTO;
import io.spring.github.api.dto.response.PaymentResponseDTO;
import io.spring.github.domain.model.ReceiptPayment;
import io.spring.github.domain.service.ReceiptPaymentService;

class ReceiptPaymentV1ControllerTest {

	@Mock
	private ReceiptPaymentService service;

	@Mock
	private ReceiptPaymentMapper mapper;

	@InjectMocks
	private ReceiptPaymentV1Controller controller;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Salva pagamento")
	void saveTest() {

		when(service.save(any())).thenReturn(mock(ReceiptPayment.class));
		when(mapper.dtoToEntity(any())).thenReturn(mock(ReceiptPayment.class));
		when(mapper.entityToDto(any())).thenReturn(mock(PaymentResponseDTO.class));

		ResponseEntity<Object> response = controller.save(mock(ReceiptPaymentRequestDTO.class));

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
