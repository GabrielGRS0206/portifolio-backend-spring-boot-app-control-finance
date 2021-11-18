package io.spring.github.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "contact")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Contact extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_company_person", nullable = false)
	private CompanyPerson companyPerson;

	@NotNull
	@NotBlank
	@Column(length = 60)
	private String contactDescription;

	@NotNull
	@NotBlank
	@Column(length = 120)
	private String detail;

	@NotNull
	@NotBlank
	@Column(length = 30)
	private String typeContact;
}
