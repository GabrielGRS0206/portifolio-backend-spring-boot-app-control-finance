package io.spring.finance.domain.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import io.spring.finance.domain.model.CompanyPerson;

public class CompanyPersonSpecification implements Specification<CompanyPerson> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "name";

	private String name;

	@Override
	public Predicate toPredicate(Root<CompanyPerson> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		return criteriaBuilder.like(root.get(NAME), "%" + NAME + "%");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
