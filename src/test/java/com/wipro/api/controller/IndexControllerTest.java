package com.wipro.api.controller;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.wipro.api.model.Message;
import com.wipro.api.services.ConnectionService;
import com.wipro.api.services.ValidationService;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

	@InjectMocks
	private IndexController indexControler;

	@InjectMocks
	private Message messageResponse;

	@InjectMocks
	private Message messageToAssert;

	@Mock
	private ConnectionService connectionService;

	@Mock
	private ValidationService validationService;

	@Rule
	public ExpectedException exception;

	@Test
	public void IndexController_HealthCheckReturnsOK() {
		Mockito.when(connectionService.getHealth()).thenReturn("{\"message\":\"Up and running\"}");
		Assert.assertEquals(indexControler.index().getBody().toString(), "{\"message\":\"Up and running\"}");
	}

	@Test
	public void IndexController_HealthCheckDataBaseReturnsOK() {
		Mockito.when(connectionService.getHealthDB()).thenReturn("{\"message\":\"Up and running Database\"}");
		Assert.assertEquals(indexControler.db().getBody().toString(), "{\"message\":\"Up and running Database\"}");
	}

	@Test
	public void IndexController_MessageReturnsOK() {
		Message messageToAssert = new Message();
		messageToAssert.setDeviceChannel("Channel");
		messageToAssert.setMessageCategory("Category");
		messageToAssert.setMessageType("Type");
		Mockito.when(validationService.validation(Mockito.any(Message.class))).thenReturn(messageToAssert);
		Assert.assertEquals(indexControler.auth(messageToAssert).getBody().toString(), messageToAssert.toString());
	}
}
