package com.wipro.api.services;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

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

	@Test // getSingleResult is returning null
	public void connectionService_UpAndRunningDataBase() {
		ReflectionTestUtils.setField(connectionService, "query", "oi");
		ArrayList<String> myList = new ArrayList<String>();
		myList.add("oi");
		Query query = Mockito.mock(Query.class);
		Mockito.when(query.getResultList()).thenReturn(myList);
		Mockito.when(entityManager.createNativeQuery(Mockito.anyString())).thenReturn(Mockito.mock(Query.class));
		Assert.assertEquals(myList, connectionService.getHealthDB());
	}
}
