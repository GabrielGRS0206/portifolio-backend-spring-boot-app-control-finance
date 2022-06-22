package io.spring.github.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.spring.github.api.dto.request.ReceiptPaymentRequestDTO;
import io.spring.github.api.dto.response.DebitForPaymentResponseDTO;
import io.spring.github.api.dto.response.PaymentResponseDTO;
import io.spring.github.domain.model.ReceiptPayment;

class ReceiptPaymentMapperTest {

	@InjectMocks
	private ReceiptPaymentMapper mapper;

	@Mock
	private PaymentMapper mapperDebit;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void mapperDtoToEntityTest() {
		ReceiptPayment entity = mapper.dtoToEntity(mock(ReceiptPaymentRequestDTO.class));
		assertNotNull(entity, "entity is null");
	}

	@Test
	void mapperEntityToDtoTest() {

		DebitForPaymentResponseDTO debit = new DebitForPaymentResponseDTO();
		debit.setId(1l);

		when(mapperDebit.entityToDto(any())).thenReturn(debit);
		ReceiptPayment entity = new ReceiptPayment();
		entity.setId(1l);

		PaymentResponseDTO response = mapper.entityToDto(entity);
		assertNotNull(response, "response is null");
	}
}
