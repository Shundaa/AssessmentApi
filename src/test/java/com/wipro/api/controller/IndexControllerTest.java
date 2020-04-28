package com.wipro.api.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

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
	
	@Test
	public void indexController_HealthCheckReturnsOK() {
		indexControler.index();
		Mockito.verify(connectionService,Mockito.times(1)).getHealth();
	}

	@Test
	public void indexController_HealthCheckDataBaseReturnsOK() {
		indexControler.db();
		Mockito.verify(connectionService,Mockito.times(1)).getHealthDB();
	}

	@Test
	public void indexController_MessageReturnsOK() {
		indexControler.auth(Mockito.any());
		Mockito.verify(validationService,Mockito.times(1)).validation(Mockito.any());
	}
}
