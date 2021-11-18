package io.spring.github.domain.utils;

import java.sql.Date;
import java.time.LocalDate;

public class Utilities {

	/**
	 * Remove caracteres da string
	 * 
	 * @param value = valor com caracteres
	 * @return value sem caracteress
	 */
	public static String removeCaracteres(String value) {
		if (value == null) {
			return value;
		}
		return value.replaceAll("[^0123456789]", "");
	}

	public static String replic(String value, int x) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < x;i++) {
			sb.append(value);
		}
		return sb.toString();
	}

	public static LocalDate removeNull(LocalDate localDate) {
		return localDate != null ? localDate : LocalDate.now();
	}

	public static Date removeNull(Date date) {
		return date != null ? date : new Date(1l);
	}
}
