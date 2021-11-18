package io.spring.github.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "company_person")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CompanyPerson extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotBlank
	@Column(length = 120)
	private String name;

	@NotNull
	@NotBlank
	@Size(min = 0, max = 14)
	@Column(length = 14)
	private String document;

	@NotNull
	@NotBlank
	@Column(name = "type_person")
	private String typePerson;

	@Column(length = 9)
	private String cep;

	@Column(length = 80)
	private String district;

	@Column(length = 120)
	private String street;

	@Column(length = 100)
	private String city;

	@OneToMany(mappedBy = "companyPerson", fetch = FetchType.LAZY)
	private List<Contact> contacts;

	public CompanyPerson(Long id) {
		this.id = id;
	}

	public CompanyPerson() {
		super();
	}
}
