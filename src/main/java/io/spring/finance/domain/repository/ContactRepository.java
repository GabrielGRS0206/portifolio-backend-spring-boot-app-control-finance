package io.spring.finance.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.spring.finance.domain.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
