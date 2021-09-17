package io.spring.finance.api.mapper.dto.request;

public class ContactRequestDTO {

	private String contactDescription;
	private String detail;
	private String typeContact;
	
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
