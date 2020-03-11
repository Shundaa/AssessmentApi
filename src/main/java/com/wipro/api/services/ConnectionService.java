package com.wipro.api.services;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

@Service
@PropertySource("classpath:queries.properties")
public class ConnectionService {

	@Autowired
	private EntityManager entityManager;

	@Value("${spring.queries.status-query}")
	private String query;

	public JsonObject getHealth() {
		JsonObject message = new JsonObject();
		message.addProperty("message", "UP and Running");
		return message;
	}

	public JsonObject getHealthDB() {
		JsonObject message = new JsonObject();
		message.addProperty("message", entityManager.createNativeQuery(query).getResultList().toString());
		return message;
	}
}
