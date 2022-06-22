package io.spring.github.api.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.spring.github.api.dto.response.CepResponseDTO;
import io.spring.github.domain.model.CepResponse;

class CepMapperTest {

	private CepMapper mapper;

	@BeforeEach
	void setUp() throws Exception {
		mapper = new CepMapper();
	}

	@Test
	void mapperTest() {
		CepResponse entity = new CepResponse();
		CepResponseDTO response = mapper.mapper(entity);
		assertNotNull(response, "response is null");
	}

}
