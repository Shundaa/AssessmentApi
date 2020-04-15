package com.wipro.api.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.wipro.api.model.HealthCheck;
import com.wipro.api.model.Message;
import com.wipro.api.services.ConnectionService;
import com.wipro.api.services.ValidationService;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

	@InjectMocks
	private IndexController indexControler;

	@Mock
	private ConnectionService connectionService;

	@Mock
	private ValidationService validationService;


	private Message messageToAssert;
	
	private HealthCheck healthCheckToAssert;
	
	@Rule
	public ExpectedException exception;
	
	@Before
	public void init() {
		messageToAssert = new Message();
		healthCheckToAssert = new HealthCheck();
	}
	
	@Test
	public void indexController_HealthCheckReturnsOK() {
		healthCheckToAssert.setMessage("Up and running to assert");
		Mockito.when(connectionService.getHealth()).thenReturn(healthCheckToAssert);
		Assert.assertEquals(indexControler.index().getBody(),healthCheckToAssert);
	}

	@Test
	public void indexController_HealthCheckDataBaseReturnsOK() {
		healthCheckToAssert.setMessage("Up and running DB to assert");
		Mockito.when(connectionService.getHealthDB()).thenReturn(healthCheckToAssert);
		Assert.assertEquals(indexControler.db().getBody(), healthCheckToAssert);
	}

	@Test
	public void indexController_MessageReturnsOK() {
		messageToAssert.setDeviceChannel("Channel");
		messageToAssert.setMessageCategory("Category");
		messageToAssert.setMessageType("Type");
		Mockito.when(validationService.validation(Mockito.any(JsonNode.class))).thenReturn(messageToAssert);
		Assert.assertEquals(indexControler.auth(Mockito.any(JsonNode.class)).getBody(), messageToAssert);
	}
}
