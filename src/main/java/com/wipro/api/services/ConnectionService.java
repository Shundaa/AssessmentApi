package com.wipro.api.services;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.wipro.api.model.HealthCheck;

@Service
@PropertySource("classpath:queries.properties")
public class ConnectionService {

	@Autowired
	private EntityManager entityManager;

	@Value("${spring.queries.status-query}")
	private String query;
	
	private String healthResponse = "Up and running";
	
	private HealthCheck healthCheck;

	@PostConstruct
	public void init() {
		healthCheck = new HealthCheck();
	}

	public HealthCheck getHealth() {
		healthCheck.setMessage(healthResponse);
		return healthCheck;
	}

	public HealthCheck getHealthDB() {
		healthCheck.setMessage(entityManager.createNativeQuery(query).getSingleResult().toString());
		return healthCheck;
	}
}
