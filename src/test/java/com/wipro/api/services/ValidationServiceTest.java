package com.wipro.api.services;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.JsonNode;
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

	@Test
	public void validationService_ValidateDeviceChannelValues() {
		message.("DeviceChannel","01");
		messageResponse = validationService.validation(message);
		Assert.assertEquals(messageResponse, message);
	}

	@Test
	public void validationService_ValidateMessageCategoryValues() {
		message.setMessageCategory("01");
		messageResponse = validationService.validation(message);
		Assert.assertEquals(messageResponse, message);
	}

	@Test
	public void validationService_ValidatMessageTypeValues() {
		message.setMessageType("Erro");
		messageResponse = validationService.validation(message);
		Assert.assertEquals(messageResponse, message);
		message.setMessageType("AReq");
		messageResponse = validationService.validation(message);
		Assert.assertEquals(messageResponse, message);
	}

	@Test
	public void validationService_ValidateMessageCategoryDeviceChannelValue() {
		message.setMessageCategory("01");
		message.setDeviceChannel("02");
		messageResponse = validationService.validation(message);
		Assert.assertEquals(messageResponse, message);
	}

	@Test
	public void validationService_ValidateMessageCategoryDeviceChannelAndMessageTypeValue() {
		message.setMessageCategory("01");
		message.setDeviceChannel("02");
		message.setMessageType("AReq");
		messageResponse = validationService.validation(message);
		Assert.assertEquals(messageResponse, message);
	}

	@Test
	public void validationService_ValidateNullValue() {
		messageResponse = validationService.validation(message);
		Assert.assertEquals(messageResponse, message);
	}

	@Test
	public void validationService_ValidateDeviceChannelShouldThrowException() {
		message.setDeviceChannel("01a");
		exception.expect(ValidationException.class);
		validationService.validation(message);
	}

	@Test
	public void validationService_ValidateMessageCategoryShouldThrowException() {
		message.setMessageCategory("00");
		exception.expect(ValidationException.class);
		validationService.validation(message);
	}

	@Test
	public void validationService_ValidatMessageTypeShouldThrowException() {
		message.setMessageType("Eror");
		exception.expect(ValidationException.class);
		validationService.validation(message);
	}

}
