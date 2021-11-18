package io.spring.github.api.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.spring.github.api.mapper.dto.request.ReceiptPaymentRequestDTO;
import io.spring.github.api.mapper.dto.response.PaymentResponseDTO;
import io.spring.github.domain.model.Payment;
import io.spring.github.domain.model.ReceiptPayment;

@Component
public class ReceiptPaymentMapper {
	
	@Autowired
	private PaymentMapper mapper;

	public ReceiptPayment dtoToEntity(ReceiptPaymentRequestDTO request) {
		var entity = new ReceiptPayment();
		entity.setDebitForPayment(new Payment(request.getIdDebitForPayment()));
		entity.setReceivement(request.getReceivement());
		entity.setAmount(request.getAmount());
		entity.setFees(request.getFees());
		entity.setFine(request.getFine());
		entity.setDiscount(request.getDiscount());
		return entity;
	}

	public PaymentResponseDTO entityToDto(ReceiptPayment entity) {
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
