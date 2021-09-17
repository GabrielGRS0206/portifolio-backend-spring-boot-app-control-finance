package io.spring.finance.api.mapper.dto.response;

public class ContactResponseDTO {

	private Long id;
	private Long idCompanyPerson;
	private String contactDescription;
	private String detail;
	private String typeContact;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdCompanyPerson() {
		return idCompanyPerson;
	}
	public void setIdCompanyPerson(Long idCompanyPerson) {
		this.idCompanyPerson = idCompanyPerson;
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
