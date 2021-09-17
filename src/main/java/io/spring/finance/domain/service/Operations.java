package io.spring.finance.domain.service;

import java.util.List;

public interface Operations <T>{

	T save(T object);
	T update(T object,Long id);
	T findById(Long id);
	boolean existsById(Long id);
	void delete(Long id);
	List<T> findAll();
	
}
