package io.spring.github.domain.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.github.domain.exception.business.BusinessException;
import io.spring.github.domain.exception.business.MessageException;
import io.spring.github.domain.model.CompanyPerson;
import io.spring.github.domain.model.Payment;
import io.spring.github.domain.model.StatusPayment;
import io.spring.github.domain.repository.PaymentRepository;

@Service
public class PaymentService implements Operations<Payment> {

	@Autowired
	private PaymentRepository repository;

	@Autowired
	private CompanyPersonService companyPersonService;

	@Override
	public Payment save(Payment entity) {
		Objects.requireNonNull(entity, MessageException.OBJECT_NOT_NULL.getValue());
		verifyCompanyPersonExists(entity.getCompany());
		return repository.save(entity);
	}

	private void verifyCompanyPersonExists(CompanyPerson company) {
		if (!companyPersonService.existsById(company.getId())) {
			throw new BusinessException(MessageException.COMPANY_PERSON_NOT_FOUND.getValue(),company.getId());
		}
	}

	@Override
	public Payment update(Payment entity, Long id) {
		entity.setId(id);
		verifyCompanyPersonExists(entity.getCompany());
		return repository.save(entity);
	}

	@Override
	public Payment findById(Long id) {
		Optional<Payment> entity = repository.findById(id);

		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new BusinessException(MessageException.DEBIT_FOR_PAYMENT_NOT_FOUND.getValue(),id);
		}
	}

	@Override
	public void delete(Long id) {
		Payment entity = findById(id);

		if (entity.getStatus().equals(StatusPayment.SETTLED.getDescription())) {
			throw new BusinessException("Conta quitada não é possível deletar");
		} else {
			repository.deleteById(id);
		}
	}

	@Override
	public List<Payment> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}

	public List<Payment> listDebitForPaymentOpen(Long id) {
		return repository.findAllDebitForPaymentOpen(id);
	}

	public List<Payment> saveList(List<Payment> list) {
		for (Payment entity : list) {
			verifyCompanyPersonExists(entity.getCompany());
		}
		return repository.saveAll(list);
	}
}
