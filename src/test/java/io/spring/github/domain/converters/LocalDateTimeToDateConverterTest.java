package io.spring.github.domain.converters;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class LocalDateTimeToDateConverterTest {

	private LocalDateTimeToDateConverter converter = new LocalDateTimeToDateConverter();

	@Test
	void convertTimeStampTest() {

		Timestamp timeStamp = converter.convertToDatabaseColumn(LocalDateTime.now());
		assertNotNull(timeStamp);

		Timestamp timeStamp2 = converter.convertToDatabaseColumn(null);
		assertNull(timeStamp2);
	}
	
	@Test
	void convertToEntityTest() {

		LocalDateTime localDate = converter.convertToEntityAttribute(Timestamp.from(Instant.now()));
		assertNotNull(localDate);

		LocalDateTime localDate2 = converter.convertToEntityAttribute(null);
		assertNull(localDate2);
	}

}
