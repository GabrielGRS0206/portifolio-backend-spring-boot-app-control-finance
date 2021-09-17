package io.spring.finance.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.spring.finance.api.mapper.CompanyPersonMapper;
import io.spring.finance.api.mapper.dto.request.CompanyPersonRequestDTO;
import io.spring.finance.api.mapper.dto.response.CompanyPersonResponseDTO;
import io.spring.finance.domain.model.CompanyPerson;
import io.spring.finance.domain.service.CompanyPersonService;
import io.spring.finance.domain.specification.CompanyPersonSpecification;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/people companies")
public class CompanyPersonV1Controller extends BaseController {

	@Autowired
	private CompanyPersonService service;

	@Autowired
	private CompanyPersonMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Adicionar pessoa empresa")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pessoa ou empresa adicionada com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> save(@RequestBody @Valid CompanyPersonRequestDTO request) {
		CompanyPerson entity = mapper.dtoToEntity(request);
		service.save(entity);
		CompanyPersonResponseDTO response = mapper.entityToDto(entity);
		return created(response);
	}

	@GetMapping("/{id}")
	@ApiOperation("Consultar pessoa ou empresa por código")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pessoa ou empresa recuperado com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		CompanyPerson entity = service.findById(id);
		CompanyPersonResponseDTO response = mapper.entityToDto(entity);
		return ok(response);
	}

	@GetMapping
	@ApiOperation("Listar todas pessoas e empresas")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Dados recuperados com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public List<CompanyPersonResponseDTO> findAll() {
		return service.findAll().stream().map(e -> mapper.entityToDto(e)).collect(Collectors.toList());
	}

	@PutMapping("/{id}")
	@ApiOperation("Alterar pessoa ou empresa")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pessoa ou empresa alterada com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> update(@Valid @PathVariable Long id, @RequestBody CompanyPersonRequestDTO request) {
		CompanyPerson entity = mapper.dtoToEntity(request);
		service.update(entity, id);
		CompanyPersonResponseDTO response = mapper.entityToDto(entity);
		return ok(response);
	}

	@GetMapping("/document/{document}")
	@ApiOperation("Consultar pessoa ou empresa por CPF/CNPJ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pessoa ou empresa recuperada com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> findByCpfOrCnpj(@PathVariable String document) {
		CompanyPersonResponseDTO response = mapper.entityToDto(service.findByDocument(document));
		return ok(response);
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Excluir pessoa ou empresa")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pessoa ou empresa excluida com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@GetMapping("/filter")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pessoas e empresas recuperadas com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public Page<CompanyPersonResponseDTO> filterName(CompanyPersonSpecification filter, Pageable pageable) {

		Page<CompanyPerson> list = service.search(filter, pageable);
		return new PageImpl<>(list.getContent().stream().map(x -> mapper.entityToDto(x)).collect(Collectors.toList()),
				list.getPageable(), list.getTotalElements());
	}
}
