package com.grocery.store.services;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class QueryLoaderService {

	private static QueryLoaderService instance;
	
	private Map<String, String> queries;
	
	public QueryLoaderService() {
		loadQueries();
	}

	private void loadQueries() {
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			queries = objectMapper.readValue(
					Paths.get("src/main/resources/queries.json").toFile(),
					new TypeReference<Map<String, String>>() {}
					);
		}catch(IOException e) {
			throw new RuntimeException("Failed to load queries.json", e);
		}
	}
	
	public String getQuery(String key) {
        return queries.get(key);
    }
	
	public static synchronized QueryLoaderService getInstance() {
        if (instance == null) {
            instance = new QueryLoaderService();
        }
        return instance;
    }
}
