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
import org.springframework.test.util.ReflectionTestUtils;

import com.google.gson.JsonObject;


@RunWith(MockitoJUnitRunner.class)
public class ConnectionServiceTest {

	@InjectMocks
	ConnectionService connectionService;

	@Mock
	EntityManager entityManager;

	@Test
	public void connectionService_UpAndRunning() {
		JsonObject message = new JsonObject();
		message.addProperty("message", "UP and Running");
		Assert.assertEquals(message, connectionService.getHealth());
	}
	
	//@Test query on services is null????@value?
	public void connectionService_UpAndRunningDataBase() {
		JsonObject message = new JsonObject();
		message.addProperty("message", "UP and Running DATA BASE");
		
		Query query = Mockito.mock(Query.class);
		Mockito.when(entityManager.createNativeQuery(Mockito.anyString())).thenReturn(Mockito.mock(Query.class));
		Mockito.when(query.getSingleResult()).thenReturn(message);
		Assert.assertEquals(message.toString(), connectionService.getHealthDB().toString());
	}
}

