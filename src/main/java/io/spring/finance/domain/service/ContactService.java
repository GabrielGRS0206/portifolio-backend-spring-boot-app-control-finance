package io.spring.finance.domain.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.finance.domain.exception.model_exception.ContactNotFoundException;
import io.spring.finance.domain.model.Contact;
import io.spring.finance.domain.repository.ContactRepository;

@Service
public class ContactService implements Operations<Contact> {

	@Autowired
	private ContactRepository repository;

	@Override
	public Contact save(Contact entity) {
		Objects.requireNonNull(entity, "Objeto nao pode ser null");
		return repository.save(entity);
	}

	@Override
	public Contact update(Contact entity,Long id) {
		entity.setId(id);
		return repository.save(entity);
	}

	@Override
	public Contact findById(Long id) {
		Optional<Contact> entity = repository.findById(id);

		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new ContactNotFoundException(id);
		}
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<Contact> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}
}
