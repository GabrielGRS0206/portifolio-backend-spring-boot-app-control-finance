package io.spring.finance.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.spring.finance.api.mapper.dto.request.DebitForPaymentRequestDTO;
import io.spring.finance.api.mapper.dto.response.DebitForPaymentResponseDTO;
import io.spring.finance.domain.model.CompanyPerson;
import io.spring.finance.domain.model.DebitForPayment;
import io.spring.finance.domain.utils.UtilsEmun;

class DebitForPaymentMapperTest {

	private DebitForPaymentMapper mapper = new DebitForPaymentMapper();
	private static final String VALUE_EMPTY = UtilsEmun.VALUE_EMPTY.getValue();

	@Test
	@DisplayName("Dto para entidade")
	void mapperDtoToEntityTest() {

		DebitForPaymentRequestDTO request = new DebitForPaymentRequestDTO();
		request.setValue(BigDecimal.ZERO);
		request.setDueDate(LocalDate.now());
		request.setEmission(LocalDate.now());
		request.setDescription(VALUE_EMPTY);
		request.setObservation(VALUE_EMPTY);
		request.setParcel(VALUE_EMPTY);

		DebitForPayment entity = mapper.dtoToEntity(request);
		assertNotNull(entity, "entity is null");

		assertEquals(entity.getValue(), BigDecimal.ZERO);
		assertEquals(entity.getDueDate(), LocalDate.now());
		assertEquals(entity.getEmission(), LocalDate.now());
		assertEquals(entity.getDescription(), VALUE_EMPTY);
		assertEquals(entity.getObservation(), VALUE_EMPTY);
		assertEquals(entity.getParcel(), VALUE_EMPTY);
	}

	@Test
	@DisplayName("Entidade para Dto")
	void mapperEntityToDtoTest() {

		DebitForPayment entity = new DebitForPayment();
		entity.setCompany(new CompanyPerson(1L));
		entity.setId(1l);
		entity.setValue(BigDecimal.ZERO);
		entity.setDueDate(LocalDate.now());
		entity.setEmission(LocalDate.now());
		entity.setDescription(VALUE_EMPTY);
		entity.setObservation(VALUE_EMPTY);
		entity.setParcel(VALUE_EMPTY);
		entity.setStatus(VALUE_EMPTY);
		DebitForPaymentResponseDTO response = mapper.entityToDto(entity);
		assertNotNull(response, "response is null");
		assertEquals(response.getParcel(), VALUE_EMPTY);
		assertEquals(response.getId(), 1l);
		assertEquals(response.getValue(), BigDecimal.ZERO);
		assertEquals(response.getDueDate(), LocalDate.now());
		assertEquals(response.getEmission(), LocalDate.now());
		assertEquals(response.getStatus(), VALUE_EMPTY);
		assertEquals(response.getObservation(), VALUE_EMPTY);
		assertEquals(response.getDescription(), VALUE_EMPTY);
		assertEquals(response.getIdCompanyPerson(), 1l);
		
	}

}
