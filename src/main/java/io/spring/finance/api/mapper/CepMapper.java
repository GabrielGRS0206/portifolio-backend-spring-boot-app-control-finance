package io.spring.finance.api.mapper;

import org.springframework.stereotype.Component;

import io.spring.finance.api.mapper.dto.response.CepResponseDTO;
import io.spring.finance.domain.service.CepResponse;

@Component
public class CepMapper {

	public CepResponseDTO mapper(CepResponse entity) {
		var response = new CepResponseDTO();
		response.setCep(entity.getCep());
		response.setBairro(entity.getBairro());
		response.setComplemento(entity.getComplemento());
		response.setDdd(entity.getDdd());
		response.setIbge(entity.getIbge());
		response.setLocalidade(entity.getLocalidade());
		response.setLogradouro(entity.getLogradouro());
		response.setUf(entity.getUf());
		return response  ;
	}

	
}
