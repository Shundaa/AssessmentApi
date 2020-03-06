package com.wipro.api.services;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OracleConnectionService {
	
	@Autowired
	EntityManager entitymanger;
	
	@Value("${spring.queries.status-query}") 
	private String query;
	
	public String getHealth() {
		//need to do a database fail when is down
		return entitymanger.createNativeQuery(query).getResultList().toString();
	}
}
