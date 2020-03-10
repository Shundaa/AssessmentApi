package com.wipro.api.controller;

import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import com.wipro.api.services.ConnectionService;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class IndexControllerTest {
	
	@Mock
	private ConnectionService connectionService;
	
	@Autowired
	private IndexController indexControler;
	@Test
	public void IndexController_HealthCheckReturnsOK() throws Exception {

		Mockito.when(connectionService.getHealth()).thenReturn("[UP and Running]");
		ResponseEntity<String> response = indexControler.index();
		assertEquals(response.getBody(),"\"[UP and Running]\"");
	}
	@Test
	public void IndexController_HealthCheckDataBaseReturnsOK() throws Exception {
		Mockito.when(connectionService.getHealthDB()).thenReturn("[UP and Running DATA BASE]");
		ResponseEntity<String> response = indexControler.db();
		assertEquals(response.getBody(),"\"[UP and Running DATA BASE]\"");
	}
 }
