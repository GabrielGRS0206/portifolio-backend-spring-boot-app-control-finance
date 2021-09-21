package io.spring.finance.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.finance.domain.utils.Utilities;

@Service
public class CepService {

	@Autowired
	private ViaCEPClient cepClient;

	public CepResponse searchCep(String cep) {
		String cepFormat = Utilities.removeCaracteres(cep);
		return cepClient.searchCEP(cepFormat);
	}
}
