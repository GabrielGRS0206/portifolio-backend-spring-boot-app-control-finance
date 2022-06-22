package io.spring.github.api.mapper;

import org.springframework.stereotype.Component;

import io.spring.github.api.dto.request.PaymentRequestDTO;
import io.spring.github.api.dto.response.DebitForPaymentResponseDTO;
import io.spring.github.domain.model.CompanyPerson;
import io.spring.github.domain.model.Payment;
import io.spring.github.domain.model.StatusPayment;

@Component
public class PaymentMapper {

	public Payment dtoToEntity(PaymentRequestDTO request) {
		var entity = new Payment();
		entity.setCompany(new CompanyPerson(request.getIdCompany()));
		entity.setDescription(request.getDescription());
		entity.setObservation(request.getObservation());
		entity.setDueDate(request.getDueDate());
		entity.setEmission(request.getEmission());
		entity.setStatus(StatusPayment.OPEN.getDescription());
		entity.setValue(request.getValue());
		entity.setParcel(request.getParcel());
		return entity;
	}

	public DebitForPaymentResponseDTO entityToDto(Payment entity) {
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
