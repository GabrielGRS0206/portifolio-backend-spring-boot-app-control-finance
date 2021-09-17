package io.spring.finance.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.spring.finance.domain.model.CompanyPerson;
import io.spring.finance.domain.repository.infrasctruture.repository.company_person.CompanyPersonRepositoryQueries;

@Repository
public interface CompanyPersonRepository extends JpaRepository<CompanyPerson, Long>,CompanyPersonRepositoryQueries{

	Optional<CompanyPerson> findByDocument(String document);
}
