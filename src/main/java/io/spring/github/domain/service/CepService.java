package io.spring.github.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.github.domain.model.CepResponse;
import io.spring.github.domain.utils.Utilities;

@Service
public class CepService {

	@Autowired
	private ConsultCep cepClient;

	public CepResponse searchCep(String cep) {
		String cepFormat = Utilities.removeCaracteres(cep);
		return cepClient.searchCEP(cepFormat);
	}
}
