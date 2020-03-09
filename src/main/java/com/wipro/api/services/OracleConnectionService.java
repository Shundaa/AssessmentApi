package com.wipro.api.services;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:queries.properties")
public class OracleConnectionService {
	
	@Autowired
	EntityManager entityManager;
	
	@Value("${spring.queries.status-query}") 
	private String query;
	
	public String getHealth() {
		return entityManager.createNativeQuery(query).getResultList().toString();
	}
}
