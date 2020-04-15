package com.wipro.api.services;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.wipro.api.model.HealthCheck;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionServiceTest {

	@InjectMocks
	private ConnectionService connectionService;

	@Mock
	private EntityManager entityManager;
	
	private HealthCheck healthCheckToAssert;
	
	private HealthCheck healthCheckResponse;

	@Before
	public void init() {
		healthCheckToAssert = new HealthCheck();
	}

	@Test
	public void connectionService_UpAndRunning() {
		healthCheckToAssert.setMessage("Up and running");
		healthCheckResponse = connectionService.getHealth();
		Assert.assertEquals(healthCheckResponse,healthCheckToAssert);
	}

	@Test
	public void connectionService_UpAndRunningDataBase() {
		String toAssert = "Value to assert";
		Query query = Mockito.mock(Query.class);
		Mockito.when(query.getSingleResult()).thenReturn(toAssert);
		Mockito.when(entityManager.createNativeQuery(Mockito.anyString())).thenReturn(query);
		healthCheckResponse = connectionService.getHealthDB();
		healthCheckToAssert.setMessage(toAssert);
		Assert.assertEquals(healthCheckToAssert, healthCheckResponse);
	}
}
