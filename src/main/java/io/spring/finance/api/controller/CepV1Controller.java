package io.spring.finance.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.spring.finance.api.mapper.CepMapper;
import io.spring.finance.domain.service.CepResponse;
import io.spring.finance.domain.service.CepService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET })
@RequestMapping("/api/v1/cep")
public class CepV1Controller extends BaseController {

	@Autowired
	private CepService service;

	@Autowired
	private CepMapper mapper;

	@GetMapping("/{cep}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Consultar CEP")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "CEP recuperado com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> searchCep(@PathVariable String cep) {
		CepResponse entity = service.searchCep(cep);
		return ok(mapper.mapper(entity));
	}

}
