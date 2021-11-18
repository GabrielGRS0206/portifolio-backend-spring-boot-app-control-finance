package io.spring.github.api.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import io.spring.github.api.controller.DebitForPaymentV1Controller;
import io.spring.github.api.mapper.DebitForPaymentMapper;
import io.spring.github.api.mapper.dto.request.DebitForPaymentRequestDTO;
import io.spring.github.api.mapper.dto.response.DebitForPaymentResponseDTO;
import io.spring.github.domain.model.DebitForPayment;
import io.spring.github.domain.service.DebitForPaymentService;

class DebitForPaymentV1ControllerTest {

	@Mock
	private DebitForPaymentService service;

	@InjectMocks
	private DebitForPaymentV1Controller controller;
	
	@Mock
	private DebitForPaymentMapper mapper;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Salva 1 conta a pagar")
	void saveTest() {

		when(mapper.dtoToEntity(any())).thenReturn(mock(DebitForPayment.class));
		when(mapper.entityToDto(any())).thenReturn(mock(DebitForPaymentResponseDTO.class));
		when(service.save(any())).thenReturn(mock(DebitForPayment.class));
		
		ResponseEntity<Object> response = controller.save(any());
		
		verify(mapper,times(1)).dtoToEntity(any());
		verify(mapper,times(1)).entityToDto(any());
		verify(service,times(1)).save(any());
		
		assertNotNull(response);
	}
	
	@Test
	@DisplayName("Deleta pagamento")
	void deleteTest() {
		controller.delete(1l);
		verify(service,times(1)).delete(any());
	}
	
	@Test
	@DisplayName("Insere varias contas a pagar")
	void listTest() {
		
		when(mapper.dtoToEntity(any())).thenReturn(mock(DebitForPayment.class));
		when(service.saveList(any())).thenReturn(Arrays.asList(new DebitForPayment()));
		when(mapper.entityToDto(any())).thenReturn(mock(DebitForPaymentResponseDTO.class));
		
		ResponseEntity<Object> response =  controller.saveList(Arrays.asList(new DebitForPaymentRequestDTO()));
		assertNotNull(response);
		verify(mapper, times(1)).dtoToEntity(any());
		verify(mapper, times(1)).entityToDto(any());
		verify(service,times(1)).saveList(any());
	}
}
