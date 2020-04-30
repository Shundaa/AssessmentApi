package com.wipro.api.services;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
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

	private Logger logger = Logger.getLogger(ConnectionService.class);

	private String healthResponse = "Up and running";
	
	private HealthCheck healthCheck;

	@PostConstruct
	public void init() {
		healthCheck = new HealthCheck();
	}

	public HealthCheck getHealth() {
		healthCheck.setMessage(healthResponse);
		logger.info("Health ok");
		return healthCheck;
	}

	public HealthCheck getHealthDB() {
		healthCheck.setMessage(entityManager.createNativeQuery(query).getSingleResult().toString());
		logger.info("Database Health ok");
		return healthCheck;
	}
}
