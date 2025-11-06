package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import com.api.request.model.UserCredentials;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperDemo {
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/demo.json");
		ObjectMapper objectMapper = new ObjectMapper();
		//UserCredentials userCredentials =objectMapper.readValue(is, UserCredentials.class);
		//List userCredentials =objectMapper.readValue(is, List.class);
		UserCredentials[] userCredentialsArray =objectMapper.readValue(is, UserCredentials[].class);

		List<UserCredentials>userCredentialsList=Arrays.asList(userCredentialsArray);
		userCredentialsList.iterator();
	}
}
