package io.spring.github.domain.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.spring.github.domain.exception.business.BusinessException;
import io.spring.github.domain.exception.business.MessageException;
import io.spring.github.domain.model.CompanyPerson;
import io.spring.github.domain.model.Contact;
import io.spring.github.domain.repository.CompanyPersonRepository;
import io.spring.github.domain.service.validation.CompanyPersonValidation;
import io.spring.github.domain.specification.CompanyPersonSpecification;

@Service
public class CompanyPersonService implements Operations<CompanyPerson> {

	@Autowired
	private CompanyPersonRepository repository;

	@Autowired
	private ContactService contactService;

	@Autowired
	private CompanyPersonValidation validation;

	@Override
	public CompanyPerson save(CompanyPerson object) {
		Objects.requireNonNull(object, MessageException.OBJECT_NOT_NULL.getValue());

		Optional<CompanyPerson> companyPerson = repository.findByDocument(object.getDocument());
		
		if(companyPerson.isPresent()) {
			throw new BusinessException(MessageException.COMPANY_PERSON_EXITS.getValue(),companyPerson.get().getDocument());
		}
		
		CompanyPerson entity = repository.save(object);
		saveContacts(entity);

		return entity;
	}

	private void saveContacts(CompanyPerson entity) {
		for (Contact contact : entity.getContacts()) {
			contact.setCompanyPerson(entity);
			contactService.save(contact);
		}
	}

	@Override
	public CompanyPerson update(CompanyPerson entity, Long id) {
		entity.setId(id);
		repository.save(entity);
		return entity;
	}

	@Override
	public CompanyPerson findById(Long id) {
		Optional<CompanyPerson> entity = repository.findById(id);

		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new BusinessException(MessageException.COMPANY_PERSON_NOT_FOUND.getValue(), id);
		}
	}

	public CompanyPerson findByDocument(String document) {
		Optional<CompanyPerson> entity = repository.findByDocument(document);

		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new BusinessException(MessageException.COMPANY_PERSON_NOT_FOUND.getValue(),document);
		}
	}

	@Override
	public void delete(Long id) {
		validation.delete(id);
	}

	@Override
	public List<CompanyPerson> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}
	
	public Page<CompanyPerson> search(CompanyPersonSpecification specification, Pageable pageable) {
		return repository.search(specification, pageable);
	}
}
