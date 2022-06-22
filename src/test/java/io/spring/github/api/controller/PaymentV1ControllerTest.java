package io.spring.github.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import io.spring.github.MockUtils;
import io.spring.github.api.mapper.PaymentMapper;
import io.spring.github.api.dto.request.PaymentRequestDTO;
import io.spring.github.api.dto.response.DebitForPaymentResponseDTO;
import io.spring.github.domain.model.Payment;
import io.spring.github.domain.service.PaymentService;

class PaymentV1ControllerTest {

	@Mock
	private PaymentService service;

	@InjectMocks
	private PaymentV1Controller controller;
	
	@Mock
	private PaymentMapper mapper;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void saveTest() {

		when(mapper.dtoToEntity(any())).thenReturn(mock(Payment.class));
		when(mapper.entityToDto(any())).thenReturn(mock(DebitForPaymentResponseDTO.class));
		when(service.save(any())).thenReturn(mock(Payment.class));
		
		ResponseEntity<Object> response = controller.save(any());
		
		verify(mapper,times(1)).dtoToEntity(any());
		verify(mapper,times(1)).entityToDto(any());
		verify(service,times(1)).save(any());
		
		assertNotNull(response);
	}
	
	@Test
	void deleteTest() {
		controller.delete(1l);
		verify(service,times(1)).delete(any());
	}
	
	@Test
	void listTest() {
		
		when(mapper.dtoToEntity(any())).thenReturn(mock(Payment.class));
		when(service.saveList(any())).thenReturn(Arrays.asList(new Payment()));
		when(mapper.entityToDto(any())).thenReturn(mock(DebitForPaymentResponseDTO.class));
		
		ResponseEntity<Object> response =  controller.saveList(Arrays.asList(new PaymentRequestDTO()));
		assertNotNull(response);
		verify(mapper, times(1)).dtoToEntity(any());
		verify(mapper, times(1)).entityToDto(any());
		verify(service,times(1)).saveList(any());
	}
	
	@Test
	void testDebitForPaymentCompanyPerson() {
		when(mapper.entityToDto(any())).thenReturn(new DebitForPaymentResponseDTO());
		when(service.listDebitForPaymentOpen(MockUtils.getIdOne())).thenReturn(Arrays.asList(new Payment()));
		List<DebitForPaymentResponseDTO> list = controller.debitForPaymentCompanyPerson("1");
		assertNotNull(list, "list is null");
		assertEquals(list.size(), 1);
	}
}
