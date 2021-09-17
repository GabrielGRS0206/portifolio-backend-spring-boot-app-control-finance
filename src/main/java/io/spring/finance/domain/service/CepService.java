package io.spring.finance.domain.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import io.spring.finance.domain.exception.model_exception.CepNotFound;
import io.spring.finance.domain.utils.Utilities;

@Service
public class CepService {

	private static final int CODIGO_200 = 200;
	private static final String URI = "http://viacep.com.br/ws/#CEP#/json/";

	public CepResponse searchCep(String cep) {
		return requestCep(cep);
	}

	private CepResponse requestCep(String cep) {
		CepResponse response = null;
		try {
			HttpURLConnection connection = request(Utilities.removeCaracteres(cep));

			if (sucess(connection)) {
				response = response(connection);
			}

		} catch (Exception e) {
			throw new CepNotFound(cep);
		}
		return response;
	}

	private CepResponse response(HttpURLConnection connection) throws IOException {
		CepResponse response = null;
		InputStream inputStream = null;
		inputStream = (InputStream) connection.getContent();

		InputStreamReader in = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
		BufferedReader bufferedReader = new BufferedReader(in);

		StringBuilder content = new StringBuilder("");
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			content.append(line);
		}

		response = new Gson().fromJson(content.toString(), CepResponse.class);
		connection.disconnect();

		return response;
	}

	protected HttpURLConnection request(String cep) throws IOException {
		URL url = new URL(URI.replace("#CEP#", cep));
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "applicaiton/json");

		connection.setConnectTimeout(5000);
		connection.setDoInput(true);
		return connection;
	}

	protected boolean sucess(HttpURLConnection connection) throws IOException {
		return connection.getResponseCode() == CODIGO_200;
	}
}
