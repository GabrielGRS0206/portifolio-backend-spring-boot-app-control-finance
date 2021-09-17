package io.spring.finance.domain.repository.infrasctruture.repository.company_person;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.spring.finance.domain.model.CompanyPerson;
import io.spring.finance.domain.specification.CompanyPersonSpecification;

public interface CompanyPersonRepositoryQueries {

	public Page<CompanyPerson> search(CompanyPersonSpecification specification, Pageable pageable);
}
