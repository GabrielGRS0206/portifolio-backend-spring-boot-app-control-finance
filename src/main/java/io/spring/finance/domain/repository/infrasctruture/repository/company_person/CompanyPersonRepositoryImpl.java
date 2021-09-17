package io.spring.finance.domain.repository.infrasctruture.repository.company_person;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.google.common.base.Strings;

import io.spring.finance.domain.model.CompanyPerson;
import io.spring.finance.domain.specification.CompanyPersonSpecification;

@Repository
public class CompanyPersonRepositoryImpl implements CompanyPersonRepositoryQueries {

	@PersistenceContext
	public EntityManager manager;

	@Override
	public Page<CompanyPerson> search(CompanyPersonSpecification specification, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<CompanyPerson> criteria = builder.createQuery(CompanyPerson.class);

		Root<CompanyPerson> root = criteria.from(CompanyPerson.class);

		Predicate[] predicates = getPredicates(specification, root, builder);
		criteria.where(predicates);

		TypedQuery<CompanyPerson> query = manager.createQuery(criteria);
		restricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, getTotal(specification));
	}

	public Predicate[] getPredicates(CompanyPersonSpecification filter, Root<CompanyPerson> root,
			CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<>();

		if (Strings.isNullOrEmpty(filter.getName())) {
			predicates.add(builder.like(root.get(CompanyPersonSpecification.NAME),
					"%" + filter.getName().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	public void restricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	public Long getTotal(CompanyPersonSpecification specification) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<CompanyPerson> root = criteria.from(CompanyPerson.class);

		Predicate[] predicates = getPredicates(specification, root, builder);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}