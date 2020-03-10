package com.wipro.api.services;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class ConnectionServiceTest {

    @Autowired
    ConnectionService connectionService ;

    @Mock
    EntityManager entityManager;

    @Test
    public void connectionService_UpAndRunning(){
        Assert.assertEquals(connectionService.getHealth(),"[UP and Running]");
    }

    @Test
    public void connectionService_UpAndRunningDataBase(){
       // Mockito.when(entityManager.createNativeQuery(Mockito.anyString()).).thenReturn("[UP and Running DATA BASE]");
        Assert.assertEquals(connectionService.getHealthDB(), "[UP and Running DATA BASE]");
    }
}
