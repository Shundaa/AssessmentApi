package com.wipro.api.services;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionServiceTest {

	@InjectMocks
	ConnectionService connectionService;

	@Mock
	EntityManager entityManager;

	@Test
	public void connectionService_UpAndRunning() {
		Assert.assertEquals(connectionService.getHealth(), "{\"message\":\"Up and running\"}");
	}

	@Test
	public void connectionService_UpAndRunningDataBase() {
		String toAssert = "Value to assert";
		Query query = Mockito.mock(Query.class);
		Mockito.when(query.getSingleResult()).thenReturn(toAssert);
		Mockito.when(entityManager.createNativeQuery(Mockito.anyString())).thenReturn(query);
		String response = connectionService.getHealthDB();
		Assert.assertEquals(toAssert, response);
	}
}
