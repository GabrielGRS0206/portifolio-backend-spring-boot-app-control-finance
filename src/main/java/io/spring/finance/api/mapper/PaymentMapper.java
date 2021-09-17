package io.spring.finance.api.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.spring.finance.api.mapper.dto.request.PaymentRequestDTO;
import io.spring.finance.api.mapper.dto.response.PaymentResponseDTO;
import io.spring.finance.domain.model.DebitForPayment;
import io.spring.finance.domain.model.Payment;

@Component
public class PaymentMapper {
	
	@Autowired
	private DebitForPaymentMapper mapper;

	public Payment dtoToEntity(PaymentRequestDTO request) {
		var entity = new Payment();
		entity.setDebitForPayment(new DebitForPayment(request.getIdDebitForPayment()));
		entity.setReceivement(request.getReceivement());
		entity.setAmount(request.getAmount());
		entity.setFees(request.getFees());
		entity.setFine(request.getFine());
		entity.setDiscount(request.getDiscount());
		return entity;
	}

	public PaymentResponseDTO entityToDto(Payment entity) {
		var response = new PaymentResponseDTO();
		response.setId(entity.getId());
		response.setAmount(entity.getAmount());
		response.setDebitForPayment(mapper.entityToDto(entity.getDebitForPayment()));
		response.setDiscount(entity.getDiscount());
		response.setFees(entity.getFees());
		response.setFine(entity.getFine());
		response.setReceivement(entity.getReceivement());
		return response;
	}

}
