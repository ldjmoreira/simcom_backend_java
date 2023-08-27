package com.nelioalves.cursomc.resources.utils;

import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HandleInputs {

	public static String getInputParam(String searchRequest) {
		try {

			ObjectMapper objectMapper = new ObjectMapper();

			JsonNode jsonNode = objectMapper.readTree(searchRequest);
			String searchTerm = jsonNode.get("searchRequest").asText();

			return searchTerm;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String searchTerm = "";
			return searchTerm;
		}
	}
}
