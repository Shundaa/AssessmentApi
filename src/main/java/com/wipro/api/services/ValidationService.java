package com.wipro.api.services;

import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wipro.api.entity.Message;

@Service
public class ValidationService {
	public ResponseEntity<String> validation(Message message) {
		if (deviceChannelValidation(message) && messageCategoryValidation(message) && messageTypeValidation(message))
			return new ResponseEntity<>("{\"message\":\"Validation ok\"}", HttpStatus.OK);

		return  new ResponseEntity<>(
			       "{\"Message\":\"Error\"," +"\"Error code:\":\"4002\","
			      +"\"Error detail\":\"Data validation error\"}",HttpStatus.NOT_ACCEPTABLE);

	}

	private boolean messageTypeValidation(Message message) {
		return message.getMessageType() == null
				|| (Pattern.matches("[ARP]{1}[R]{1}[e]{1}[qs]{1}", message.getMessageType())
						|| Pattern.matches("Erro", message.getMessageType()));
	}

	private boolean messageCategoryValidation(Message message) {
		return message.getMessageCategory() == null || Pattern.matches("[0]{1}[1-2]{1}", message.getMessageCategory());
	}

	private boolean deviceChannelValidation(Message message) {
		return message.getDeviceChannel() == null || Pattern.matches("[0]{1}[1-3]{1}", message.getDeviceChannel());
	}
}
