package io.spring.finance.api.mapper;

import org.springframework.stereotype.Component;

import io.spring.finance.api.mapper.dto.request.DebitForPaymentRequestDTO;
import io.spring.finance.api.mapper.dto.response.DebitForPaymentResponseDTO;
import io.spring.finance.domain.model.CompanyPerson;
import io.spring.finance.domain.model.DebitForPayment;
import io.spring.finance.domain.model.StatusDebitForPayment;

@Component
public class DebitForPaymentMapper {

	public DebitForPayment dtoToEntity(DebitForPaymentRequestDTO request) {
		var entity = new DebitForPayment();
		entity.setCompany(new CompanyPerson(request.getIdCompany()));
		entity.setDescription(request.getDescription());
		entity.setObservation(request.getObservation());
		entity.setDueDate(request.getDueDate());
		entity.setEmission(request.getEmission());
		entity.setStatus(StatusDebitForPayment.OPEN.getDescription());
		entity.setValue(request.getValue());
		entity.setParcel(request.getParcel());
		return entity;
	}

	public DebitForPaymentResponseDTO entityToDto(DebitForPayment entity) {
		var response = new DebitForPaymentResponseDTO();
		response.setId(entity.getId());
		response.setValue(entity.getValue());
		response.setStatus(entity.getStatus());
		response.setParcel(entity.getParcel());
		response.setIdCompanyPerson(entity.getCompany().getId());
		response.setDescription(entity.getDescription());
		response.setObservation(entity.getObservation());
		response.setStatus(entity.getStatus());
		response.setDueDate(entity.getDueDate());
		response.setEmission(entity.getEmission());
		return response;
	}

}
