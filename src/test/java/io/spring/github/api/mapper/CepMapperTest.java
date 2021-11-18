package io.spring.github.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.spring.github.api.mapper.CepMapper;
import io.spring.github.api.mapper.dto.response.CepResponseDTO;
import io.spring.github.domain.model.CepResponse;
import io.spring.github.domain.utils.UtilsEmun;

class CepMapperTest {

	private CepMapper mapper;

	private static final String VALUE_EMPTY = UtilsEmun.VALUE_EMPTY.getValue();

	@BeforeEach
	void setUp() throws Exception {
		mapper = new CepMapper();
	}

	@Test
	void mapperTest() {
		CepResponse entity = new CepResponse();
		entity.setBairro(VALUE_EMPTY);
		entity.setCep(VALUE_EMPTY);
		entity.setComplemento(VALUE_EMPTY);
		entity.setDdd(VALUE_EMPTY);
		entity.setIbge(VALUE_EMPTY);
		entity.setUf(VALUE_EMPTY);
		entity.setLocalidade(VALUE_EMPTY);
		entity.setLogradouro(VALUE_EMPTY);

		CepResponseDTO response = mapper.mapper(entity);
		assertNotNull(response, "response is null");
		assertEquals(VALUE_EMPTY, response.getBairro());
		assertEquals(VALUE_EMPTY, response.getCep());
		assertEquals(VALUE_EMPTY, response.getComplemento());
		assertEquals(VALUE_EMPTY, response.getDdd());
		assertEquals(VALUE_EMPTY, response.getIbge());
		assertEquals(VALUE_EMPTY, response.getUf());
		assertEquals(VALUE_EMPTY, response.getBairro());
		assertEquals(VALUE_EMPTY, response.getLocalidade());
		assertEquals(VALUE_EMPTY, response.getLogradouro());

	}

}
