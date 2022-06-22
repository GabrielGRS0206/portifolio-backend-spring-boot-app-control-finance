package io.spring.github.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
public class DebitForPaymentResponseDTO {

	private Long id;
	private BigDecimal value;
	private LocalDate dueDate;
	private LocalDate emission;
	private Long idCompanyPerson;
	private String status;
	private String description;
	private String observation;
	private String parcel;
}
