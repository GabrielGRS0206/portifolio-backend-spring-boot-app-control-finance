package io.spring.finance.domain.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://viacep.com.br/ws/",name = "viacep")
public interface ViaCEPClient {
	@GetMapping("/{cep}/json")
	CepResponse searchCEP(@PathVariable("cep") String cep);
}
