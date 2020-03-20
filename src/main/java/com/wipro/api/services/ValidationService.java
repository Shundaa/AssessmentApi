package com.wipro.api.services;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.wipro.api.exception.ValidationException;
import com.wipro.api.model.ErrorMessageInvalid;
import com.wipro.api.model.Message;

@Service
public class ValidationService {
	public Message validation(Message message) {
		ArrayList<ErrorMessageInvalid> error = new ArrayList<>();
		messageTypeValidation(message, error);
		messageCategoryValidation(message, error);
		deviceChannelValidation(message, error);
		if (!error.isEmpty())
			throw new ValidationException(error);
		message.setMessageType("ARes");
		return message;
	}

	private void messageTypeValidation(Message message, ArrayList<ErrorMessageInvalid> validation) {
		if (!(message.getMessageType() == null
				|| (Pattern.matches("[ARP]{1}[R]{1}[e]{1}[qs]{1}", message.getMessageType())
						|| Pattern.matches("Erro", message.getMessageType())))) {
			validation.add(new ErrorMessageInvalid("messageType"));
		}
	}

	private void messageCategoryValidation(Message message, ArrayList<ErrorMessageInvalid> validation) {
		if (!(message.getMessageCategory() == null
				|| Pattern.matches("[0]{1}[1-2]{1}", message.getMessageCategory()))) {
			validation.add(new ErrorMessageInvalid("nessageCategory"));
		}
	}

	private void deviceChannelValidation(Message message, ArrayList<ErrorMessageInvalid> validation) {
		if (!(message.getDeviceChannel() == null || Pattern.matches("[0]{1}[1-3]{1}", message.getDeviceChannel()))) {
			validation.add(new ErrorMessageInvalid("deviceChannel"));
		}
	}

}
