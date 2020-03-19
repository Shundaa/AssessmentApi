package com.wipro.api.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.wipro.api.exception.ValidationException;
import com.wipro.api.model.Message;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@InjectMocks
	private ValidationService validationService;

	@InjectMocks
	private Message message;
	
	private Message messageResponse;

	@Test
	public void validationService_ValidateDeviceChannelValues() {
		message.setDeviceChannel("01");
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
	public void validationService_ValidateDeviceChannelShouldThrowException() {
		message.setDeviceChannel("01a");
		exception.expect(ValidationException.class);
		validationService.validation(message);	
	}
	
	@Test
	public void validationService_ValidateMessageCategoryShouldThrowException(){
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
