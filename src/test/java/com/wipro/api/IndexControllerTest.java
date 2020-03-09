package com.wipro.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class IndexControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Value("${url}") 
	private String url;
	
	@Value("${end.point.health}") 
	private String endPointHealth;
	
	@Value("${end.point.health.db}") 
	private String endPointHealthDB;
	
	@Value("${response.health}") 
	private String responseHealth;
	
	@Value("${response.health.db}") 
	private String responseHealthDB;

	//@Test
	public void IndexController_HealthCheckReturnsOK() throws Exception {

		ResponseEntity<String> response = restTemplate
				.getForEntity(url + endPointHealth, String.class);
		assertEquals(responseHealth, response.getBody());
	}
	//@Test
	public void IndexController_HealthCheckDataBaseReturnsOK() throws Exception {

		ResponseEntity<String> response = restTemplate
				.getForEntity(url + endPointHealthDB, String.class);
		assertEquals(responseHealthDB, response.getBody());
	}
 }
