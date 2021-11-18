package io.spring.github.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.spring.github.api.mapper.DebitForPaymentMapper;
import io.spring.github.api.mapper.PaymentMapper;
import io.spring.github.api.mapper.dto.request.PaymentRequestDTO;
import io.spring.github.api.mapper.dto.response.DebitForPaymentResponseDTO;
import io.spring.github.api.mapper.dto.response.PaymentResponseDTO;
import io.spring.github.domain.model.DebitForPayment;
import io.spring.github.domain.model.Payment;

class PaymentMapperTest {

	@InjectMocks
	private PaymentMapper mapper;

	@Mock
	private DebitForPaymentMapper mapperDebit;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Dto para entidade")
	void mapperDtoToEntityTest() {

		PaymentRequestDTO request = new PaymentRequestDTO();
		request.setAmount(BigDecimal.ZERO);
		request.setDiscount(BigDecimal.ZERO);
		request.setFees(BigDecimal.ZERO);
		request.setFine(BigDecimal.ZERO);
		request.setIdDebitForPayment(1l);
		request.setReceivement(LocalDate.now());
		Payment entity = mapper.dtoToEntity(mock(PaymentRequestDTO.class));
		assertNotNull(entity, "entity is null");
	}

	@Test
	@DisplayName("Entidade para Dto")
	void mapperEntityToDtoTest() {

		DebitForPaymentResponseDTO debit = new DebitForPaymentResponseDTO();
		debit.setId(1l);

		when(mapperDebit.entityToDto(any())).thenReturn(debit);
		Payment entity = new Payment();
		entity.setId(1l);
		entity.setDebitForPayment(new DebitForPayment(1l));
		entity.setAmount(BigDecimal.ZERO);
		entity.setFees(BigDecimal.ZERO);
		entity.setFine(BigDecimal.ZERO);
		entity.setDiscount(BigDecimal.ZERO);
		entity.setReceivement(LocalDate.now());

		PaymentResponseDTO response = mapper.entityToDto(entity);
		assertNotNull(response, "response is null");
		assertEquals(BigDecimal.ZERO, response.getAmount());
		assertEquals(BigDecimal.ZERO, response.getDiscount());
		assertEquals(BigDecimal.ZERO, response.getFine());
		assertEquals(BigDecimal.ZERO, response.getFees());
		assertEquals(1l, response.getId());
		assertEquals(1l, response.getDebitForPayment().getId());
		assertEquals(LocalDate.now(), response.getReceivement());

	}
}
