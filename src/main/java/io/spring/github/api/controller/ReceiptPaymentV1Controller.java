package io.spring.github.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.spring.github.api.mapper.ReceiptPaymentMapper;
import io.spring.github.api.dto.request.ReceiptPaymentRequestDTO;
import io.spring.github.api.dto.response.PaymentResponseDTO;
import io.spring.github.domain.model.ReceiptPayment;
import io.spring.github.domain.service.ReceiptPaymentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.DELETE, RequestMethod.POST })
@RequestMapping("/payments/v1")
public class ReceiptPaymentV1Controller extends BaseController{
	
	@Autowired
	private ReceiptPaymentService service;
	
	@Autowired
	private ReceiptPaymentMapper mapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Quitar conta a pagar")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Conta quitada com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public ResponseEntity<Object> save(@RequestBody @Valid ReceiptPaymentRequestDTO request) {
		ReceiptPayment entity = mapper.dtoToEntity(request);
		service.save(entity);
		PaymentResponseDTO response = mapper.entityToDto(entity);
		return created(response);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Excluir pagamento")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pagamento excluido com sucesso"),
			@ApiResponse(code = 401, message = "Acesso não permitido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "O aplicativo servidor falhou ao processar a solicitação") })
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
} 
