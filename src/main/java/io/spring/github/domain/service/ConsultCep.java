/**
 * 
 */
package io.spring.github.domain.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import io.spring.github.domain.exception.business.BusinessException;
import io.spring.github.domain.model.CepResponse;

@Service
public class ConsultCep implements ViaCEPClient {

	private static final String UTF_8 = "UTF-8";
	private static final int CODIGO_200 = 200;
	private static final String URI = "http://viacep.com.br/ws/#CEP#/json/";
	private static final String ERRO_CONSULT_CEP = "Erro ao consultar cep";

	@Override
	public CepResponse searchCEP(String cep) {
		try {
			return requestCep(cep);
		} catch (Exception e) {
			throw new BusinessException(ERRO_CONSULT_CEP);
		}
	}

	private CepResponse requestCep(String cep) {
		CepResponse response = null;
		cep = removeCaracteres(cep);
		try {
			HttpURLConnection connection = request(cep);

			if (sucess(connection)) {
				response = response(connection);
			}

		} catch (Exception e) {}

		return response;
	}

	protected HttpURLConnection request(String cep) throws MalformedURLException, IOException, ProtocolException {
		URL url = new URL(URI.replace("#CEP#", cep));
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "applicaiton/json");

		connection.setConnectTimeout(5000);
		connection.setDoInput(true);
		return connection;
	}

	private CepResponse response(HttpURLConnection connection) throws IOException {
		CepResponse response = null;
		InputStream inputStream = null;
		inputStream = (InputStream) connection.getContent();

		InputStreamReader in = new InputStreamReader(inputStream, UTF_8);
		BufferedReader buff = new BufferedReader(in);

		StringBuilder content = new StringBuilder("");
		String line;
		while ((line = buff.readLine()) != null) {
			content.append(line);
		}

		response = new Gson().fromJson(content.toString(), CepResponse.class);
		connection.disconnect();

		return response;
	}

	public static String removeCaracteres(String value) {
		if (value == null) {
			return value;
		}
		return value.replaceAll("[^0123456789]", "");
	}

	protected boolean sucess(HttpURLConnection connection) throws IOException {
		return connection.getResponseCode() == CODIGO_200;
	}
}
