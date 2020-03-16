package com.wipro.api.services;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
@PropertySources({ @PropertySource("classpath:/AssessmentApi/src/test/resources/queries.properties"), })
public class ConnectionService {

	@Autowired
	private EntityManager entityManager;

	@Value("${spring.queries.status-query}")
	private String query;

	public String getHealth() {
		return "{\"message\":\"Up and running\"}";
	}

	public String getHealthDB() {
		Query queryNativa = entityManager.createNativeQuery(query);
		return queryNativa.getResultList().toString();
	}
}
