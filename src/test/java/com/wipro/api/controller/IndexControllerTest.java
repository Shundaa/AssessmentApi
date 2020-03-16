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
		Mockito.when(connectionService.getHealth()).thenReturn("{\"message\":\"Up and running\"}");
		Assert.assertEquals(indexControler.index().getBody().toString(),"{\"message\":\"Up and running\"}");
	}

	@Test
	public void IndexController_HealthCheckDataBaseReturnsOK() throws Exception {
		Mockito.when(connectionService.getHealthDB()).thenReturn("{\"message\":\"Up and running Database\"}");
		Assert.assertEquals(indexControler.db().getBody().toString(),"{\"message\":\"Up and running Database\"}");
	}
}
