package com.wipro.api.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.api.exception.ValidationException;
import com.wipro.api.model.Message;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@InjectMocks
	private ValidationService validationService;

	private JsonNode message;

	private Message messageResponse;
	
	private ObjectMapper mapper;
	
	@Before
	public void init() {
		messageResponse = new Message();
		mapper = new ObjectMapper();
	}
	
	@Test
	public void validationService_ValidateDeviceChannelValues() {
		messageResponse.setDeviceChannel("01");
		message = mapper.valueToTree(messageResponse);
		messageResponse = validationService.validation(message);
		Assert.assertEquals(messageResponse.getMessageType(),"ARes");
	}

	@Test
	public void validationService_ValidateMessageCategoryValues() {
		messageResponse.setMessageCategory("01");
		message = mapper.valueToTree(messageResponse);
		messageResponse = validationService.validation(message);
		Assert.assertEquals(messageResponse.getMessageType(),"ARes");
	}

	@Test
	public void validationService_ValidatMessageTypeValues() {
		messageResponse.setMessageType("Erro");
		message = mapper.valueToTree(messageResponse);
		messageResponse = validationService.validation(message);
		Assert.assertEquals(messageResponse.getMessageType(),"ARes");
		messageResponse.setMessageType("AReq");
		message = mapper.valueToTree(messageResponse);
		messageResponse = validationService.validation(message);
		Assert.assertEquals(messageResponse.getMessageType(),"ARes");
	}

	@Test
	public void validationService_ValidateMessageCategoryDeviceChannelValue() {
		messageResponse.setMessageCategory("01");
		messageResponse.setDeviceChannel("02");
		message = mapper.valueToTree(messageResponse);
		messageResponse = validationService.validation(message);
		Assert.assertEquals(messageResponse.getMessageType(),"ARes");
	}

	@Test
	public void validationService_ValidateMessageCategoryDeviceChannelAndMessageTypeValue() {
		messageResponse.setMessageCategory("01");
		messageResponse.setDeviceChannel("02");
		messageResponse.setMessageType("AReq");
		message = mapper.valueToTree(messageResponse);
		messageResponse = validationService.validation(message);
		Assert.assertEquals(messageResponse.getMessageType(),"ARes");
	}

	@Test
	public void validationService_ValidateNullValue() {
		message = mapper.valueToTree(messageResponse);
		messageResponse = validationService.validation(message);
		Assert.assertEquals(messageResponse.getMessageType(),"ARes");
	}

	@Test
	public void validationService_ValidateDeviceChannelShouldThrowException() {
		messageResponse.setDeviceChannel("01a");
		exception.expect(ValidationException.class);
		message = mapper.valueToTree(messageResponse);
		validationService.validation(message);
	}

	@Test
	public void validationService_ValidateMessageCategoryShouldThrowException() {
		messageResponse.setMessageCategory("00");
		exception.expect(ValidationException.class);
		message = mapper.valueToTree(messageResponse);
		validationService.validation(message);
	}

	@Test
	public void validationService_ValidatMessageTypeShouldThrowException() {
		messageResponse.setMessageType("Eror");
		exception.expect(ValidationException.class);
		message = mapper.valueToTree(messageResponse);
		validationService.validation(message);
	}

}
