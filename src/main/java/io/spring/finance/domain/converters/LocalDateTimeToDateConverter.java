package io.spring.finance.domain.converters;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeToDateConverter implements AttributeConverter<LocalDateTime, java.sql.Timestamp> {

	@Override
	public java.sql.Timestamp convertToDatabaseColumn(LocalDateTime value) {
		if (value == null) {
			return null;
		}

		return Timestamp.valueOf(value);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(java.sql.Timestamp value) {
		if (value == null) {
			return null;
		}

		return value.toLocalDateTime();
	}
}
