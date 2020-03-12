package com.wipro.api.controller;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.JsonObject;
import com.wipro.api.services.ConnectionService;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

	@InjectMocks
	private IndexController indexControler;

	@Mock
	private ConnectionService connectionService;

	@Rule
	public ExpectedException exception;
	
	@Test
	public void IndexController_HealthCheckReturnsOK() throws Exception {
		JsonObject message = new JsonObject();
		message.addProperty("message", "UP and Running");
		Mockito.when(connectionService.getHealth()).thenReturn(message);
		assert(!indexControler.index().getBody().isEmpty());
	}

	@Test
	public void IndexController_HealthCheckDataBaseReturnsOK() throws Exception {
		JsonObject message = new JsonObject();
		message.addProperty("message", "UP and Running DATA BASE");
		Mockito.when(connectionService.getHealthDB()).thenReturn(message);
		assert(!indexControler.db().getBody().isEmpty());
	}
}
