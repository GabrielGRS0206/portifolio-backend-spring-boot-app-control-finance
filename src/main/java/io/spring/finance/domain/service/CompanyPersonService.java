package io.spring.finance.domain.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.spring.finance.domain.exception.model_exception.CompanyPersonNotFoundException;
import io.spring.finance.domain.model.CompanyPerson;
import io.spring.finance.domain.model.Contact;
import io.spring.finance.domain.repository.CompanyPersonRepository;
import io.spring.finance.domain.service.validation.CompanyPersonValidation;
import io.spring.finance.domain.specification.CompanyPersonSpecification;

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
		Objects.requireNonNull(object, "Objeto nao pode ser null");

		Optional<CompanyPerson> companyPerson = repository.findByDocument(object.getDocument());
		
		if(companyPerson.isPresent()) {
			throw new CompanyPersonNotFoundException("Pessoa ou empresa com documento %d já adicionado");
		}
		
		CompanyPerson entity = repository.save(object);
		
		for (Contact contact : entity.getContacts()) {
			contact.setCompanyPerson(entity);
			contactService.save(contact);
		}

		return entity;
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
			throw new CompanyPersonNotFoundException(id);
		}
	}

	public CompanyPerson findByDocument(String document) {
		Optional<CompanyPerson> entity = repository.findByDocument(document);

		if (entity.isPresent()) {
			return entity.get();
		} else {
			System.out.println(">>"+document);
			throw new CompanyPersonNotFoundException(
					String.format("Pessoa empresa com documento %d não encontrada", document));
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
