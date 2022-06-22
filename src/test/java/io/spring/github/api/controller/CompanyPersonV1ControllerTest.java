package io.spring.github.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import io.spring.github.api.mapper.CompanyPersonMapper;
import io.spring.github.api.dto.request.CompanyPersonRequestDTO;
import io.spring.github.api.dto.response.CompanyPersonResponseDTO;
import io.spring.github.domain.model.CompanyPerson;
import io.spring.github.domain.service.CompanyPersonService;
import io.spring.github.domain.utils.UtilsEmun;

class CompanyPersonV1ControllerTest {

	@Mock
	private CompanyPersonService service;

	@InjectMocks
	private CompanyPersonV1Controller controller;

	@Mock
	private CompanyPersonMapper mapper;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void saveTest() {

		when(mapper.dtoToEntity(mock(CompanyPersonRequestDTO.class))).thenReturn(mock(CompanyPerson.class));
		when(service.save(any())).thenReturn(mock(CompanyPerson.class));
		when(mapper.entityToDto(mock(CompanyPerson.class))).thenReturn(mock(CompanyPersonResponseDTO.class));

		ResponseEntity<Object> response = controller.save(mock(CompanyPersonRequestDTO.class));

		verify(mapper, times(1)).dtoToEntity(any());
		verify(mapper, times(1)).entityToDto(any());
		verify(service, times(1)).save(any());

		assertNotNull(response);
	}

	@Test
	void updateTest() {

		when(mapper.dtoToEntity(mock(CompanyPersonRequestDTO.class))).thenReturn(mock(CompanyPerson.class));
		when(service.update(mock(CompanyPerson.class), 1l)).thenReturn(mock(CompanyPerson.class));
		when(mapper.entityToDto(mock(CompanyPerson.class))).thenReturn(mock(CompanyPersonResponseDTO.class));

		ResponseEntity<Object> response = controller.update(1l, mock(CompanyPersonRequestDTO.class));

		verify(mapper, times(1)).dtoToEntity(any());
		verify(mapper, times(1)).entityToDto(any());
		verify(service, times(1)).update(any(), any());

		assertNotNull(response);
	}

	@Test
	void findAllTest() {

		when(mapper.entityToDto(mock(CompanyPerson.class))).thenReturn(mock(CompanyPersonResponseDTO.class));
		when(service.findAll())
				.thenReturn(Arrays.asList(new CompanyPerson(), new CompanyPerson(), new CompanyPerson()));

		List<CompanyPersonResponseDTO> list = controller.findAll();

		verify(mapper, times(3)).entityToDto(any());
		verify(service, times(1)).findAll();

		assertNotNull(list);
		assertEquals(list.size(), UtilsEmun.THREE.getIntValue());
	}

	@Test
	void findByDocumentTest() {

		when(mapper.entityToDto(mock(CompanyPerson.class))).thenReturn(mock(CompanyPersonResponseDTO.class));
		when(service.findByDocument(any())).thenReturn(mock(CompanyPerson.class));

		ResponseEntity<Object> response = controller.findByCpfOrCnpj(any());

		verify(mapper, times(1)).entityToDto(any());
		verify(service, times(1)).findByDocument(any());

		assertNotNull(response);
	}

	@Test
	void findByIdTest() {

		when(mapper.entityToDto(mock(CompanyPerson.class))).thenReturn(mock(CompanyPersonResponseDTO.class));
		when(service.findById(any())).thenReturn(mock(CompanyPerson.class));

		ResponseEntity<Object> response = controller.findById(any());

		verify(mapper, times(1)).entityToDto(any());
		verify(service, times(1)).findById(any());

		assertNotNull(response);
	}

	@Test
	void deleteTest() {
		controller.delete(1l);
		verify(service, times(1)).delete(any());
	}
}
