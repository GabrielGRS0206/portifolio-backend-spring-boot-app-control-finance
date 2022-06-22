package io.spring.github.api.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import io.spring.github.api.dto.request.PaymentRequestDTO;
import io.spring.github.api.dto.response.DebitForPaymentResponseDTO;
import io.spring.github.domain.model.CompanyPerson;
import io.spring.github.domain.model.Payment;

class PaymentMapperTest {

	private PaymentMapper mapper = new PaymentMapper();

	@Test
	void mapperDtoToEntityTest() {
		PaymentRequestDTO request = new PaymentRequestDTO();
		Payment entity = mapper.dtoToEntity(request);
		assertNotNull(entity, "entity is null");
	}

	@Test
	void mapperEntityToDtoTest() {
		Payment entity = new Payment();
		entity.setCompany(new CompanyPerson(1l));
		DebitForPaymentResponseDTO response = mapper.entityToDto(entity);
		assertNotNull(response, "response is null");
	}

}
