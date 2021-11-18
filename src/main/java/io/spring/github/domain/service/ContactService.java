package io.spring.github.domain.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.github.domain.exception.business.BusinessException;
import io.spring.github.domain.exception.business.MessageException;
import io.spring.github.domain.model.Contact;
import io.spring.github.domain.repository.ContactRepository;

@Service
public class ContactService implements Operations<Contact> {

	@Autowired
	private ContactRepository repository;

	@Override
	public Contact save(Contact entity) {
		Objects.requireNonNull(entity, MessageException.OBJECT_NOT_NULL.getValue());
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
			throw new BusinessException(MessageException.CONTACT_NOT_FOUND.getValue(),id);
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
