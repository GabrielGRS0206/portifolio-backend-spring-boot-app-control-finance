package io.spring.github;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import io.spring.github.domain.model.CompanyPerson;

public class MockUtils {

	public static long getIdOne() {
		return 1l;
	}

	public static Long getIdTwo() {
		return 2l;
	}
}
