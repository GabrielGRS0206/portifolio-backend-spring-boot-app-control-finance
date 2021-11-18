package io.spring.github.domain.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.spring.github.domain.model.CepResponse;

//@FeignClient(url = "http://viacep.com.br/ws/",name = "viacep")
public interface ViaCEPClient {
	@GetMapping("/{cep}/json")
	CepResponse searchCEP(@PathVariable("cep") String cep);
}
