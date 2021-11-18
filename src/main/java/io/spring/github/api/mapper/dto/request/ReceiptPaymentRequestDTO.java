package io.spring.github.api.mapper.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
public class ReceiptPaymentRequestDTO {

	private Long idDebitForPayment;
	private LocalDate receivement;
	private BigDecimal amount;
	private BigDecimal fees;
	private BigDecimal fine;
	private BigDecimal discount;
}