package io.spring.github.domain.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.spring.github.domain.utils.Utilities;

class UtilitiesTest {

	private Utilities utilities = new Utilities();
	private static final String CEP = "88904326";

	@Test
	void removeCaracterestest() {
		String s = utilities.removeCaracteres("88904-326");
		assertEquals(CEP, s);
	}

}
