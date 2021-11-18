package io.spring.github.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.spring.github.api.mapper.DebitForPaymentMapper;
import io.spring.github.api.mapper.dto.request.DebitForPaymentRequestDTO;
import io.spring.github.api.mapper.dto.response.DebitForPaymentResponseDTO;
import io.spring.github.domain.model.DebitForPayment;
import io.spring.github.domain.service.DebitForPaymentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST } )
@RequestMapping("/debits/v1")
public class DebitForPaymentV1Controller extends BaseController {

	@Autowired
	private DebitForPaymentService service;

	@Autowired
	private DebitForPaymentMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Adicionar 1 conta a pagar")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Conta adicionada com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> save(@RequestBody @Valid DebitForPaymentRequestDTO request) {
		DebitForPayment entity = mapper.dtoToEntity(request);
		service.save(entity);
		DebitForPaymentResponseDTO response = mapper.entityToDto(entity);
		return created(response);
	}

	@PostMapping("/list")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Adicionar várias contas a pagar")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Conta adicionada com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> saveList(@RequestBody List<DebitForPaymentRequestDTO> request) {
		List<DebitForPayment> list = request.stream().map(e -> mapper.dtoToEntity(e)).collect(Collectors.toList());
		service.saveList(list);
		List<DebitForPaymentResponseDTO> response = list.stream().map(e -> mapper.entityToDto(e))
				.collect(Collectors.toList());
		return created(response);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Consultar conta a pagar por id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Conta adicionada com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public List<DebitForPaymentResponseDTO> debitForPaymentCompanyPerson(@PathVariable String id) {
		List<DebitForPayment> list = service.listDebitForPaymentOpen(Long.parseLong(id));

		List<DebitForPaymentResponseDTO> response = list.stream().map(e -> mapper.entityToDto(e))
				.collect(Collectors.toList());

		return response;
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Excluir conta a pagar")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Conta a pagar excluida com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
