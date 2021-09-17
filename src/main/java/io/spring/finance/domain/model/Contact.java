package io.spring.finance.domain.model;

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

@Entity
@Table(name = "contact")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CompanyPerson getCompanyPerson() {
		return companyPerson;
	}

	public void setCompanyPerson(CompanyPerson companyPerson) {
		this.companyPerson = companyPerson;
	}

	public String getContactDescription() {
		return contactDescription;
	}

	public void setContactDescription(String contactDescription) {
		this.contactDescription = contactDescription;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getTypeContact() {
		return typeContact;
	}

	public void setTypeContact(String typeContact) {
		this.typeContact = typeContact;
	}
	
	
}
