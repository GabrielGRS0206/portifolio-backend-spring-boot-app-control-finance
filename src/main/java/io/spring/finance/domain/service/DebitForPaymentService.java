package io.spring.finance.domain.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.finance.domain.exception.model_exception.CompanyPersonNotFoundException;
import io.spring.finance.domain.exception.model_exception.DebitForPaymentNotFoundException;
import io.spring.finance.domain.model.CompanyPerson;
import io.spring.finance.domain.model.DebitForPayment;
import io.spring.finance.domain.model.StatusDebitForPayment;
import io.spring.finance.domain.repository.DebitForPaymentRepository;

@Service
public class DebitForPaymentService implements Operations<DebitForPayment> {

	@Autowired
	private DebitForPaymentRepository repository;

	@Autowired
	private CompanyPersonService companyPersonService;

	@Override
	public DebitForPayment save(DebitForPayment entity) {
		Objects.requireNonNull(entity, "Objeto nao pode ser null");
		verifyCompanyPersonExists(entity.getCompany());
		return repository.save(entity);
	}

	private void verifyCompanyPersonExists(CompanyPerson company) {
		if (!companyPersonService.existsById(company.getId())) {
			throw new CompanyPersonNotFoundException(company.getId());
		}
	}

	@Override
	public DebitForPayment update(DebitForPayment entity, Long id) {
		entity.setId(id);
		verifyCompanyPersonExists(entity.getCompany());
		return repository.save(entity);
	}

	@Override
	public DebitForPayment findById(Long id) {
		Optional<DebitForPayment> entity = repository.findById(id);

		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new DebitForPaymentNotFoundException(id);
		}
	}

	@Override
	public void delete(Long id) {
		DebitForPayment entity = findById(id);

		if (entity.getStatus().equals(StatusDebitForPayment.SETTLED.getDescription())) {
			throw new DebitForPaymentNotFoundException("Conta quitada não é possível deletar");
		} else {
			repository.deleteById(id);
		}
	}

	@Override
	public List<DebitForPayment> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}

	public List<DebitForPayment> listDebitForPaymentOpen(Long id) {
		return repository.findAllDebitForPaymentOpen(id);
	}

	public List<DebitForPayment> saveList(List<DebitForPayment> list) {
		for (DebitForPayment entity : list) {
			verifyCompanyPersonExists(entity.getCompany());
		}
		return repository.saveAll(list);
	}
}
