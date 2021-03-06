package io.spring.github.api.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
public class PaymentRequestDTO {

	private BigDecimal value;
	private LocalDate dueDate;
	private LocalDate emission;
	private Long idCompany;
	private String description;
	private String observation;
	private String parcel;
}
