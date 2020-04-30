package com.wipro.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.wipro.api.exception.ValidationException;
import com.wipro.api.model.ErrorMessageInvalid;
import com.wipro.api.model.Message;

@Service
public class ValidationService {

	private String messageTypeBack = "ARes";
	private String messageTypeString = "messageType";
	private String messageCategoryString = "messageCategory";
	private String deviceChannelString = "deviceChannel";
	private Message message;
	private Logger logger = Logger.getLogger(ValidationService.class);

	public Message validation(JsonNode messageJson) {

		message = new Message();
		logger.info("Mensagem Recebida");
		List<ErrorMessageInvalid> listError = new ArrayList<>();
		if (messageJson.has(messageTypeString)) {
			messageTypeValidation(messageJson.get(messageTypeString).asText(), listError);
		}
		message.setMessageType(messageTypeBack);			
		if (messageJson.has(messageCategoryString)) {
			messageCategoryValidation(messageJson.get(messageCategoryString).asText(), listError);
			message.setDeviceChannel(messageJson.get(messageCategoryString).asText());			
		}
		if (messageJson.has(deviceChannelString)) {
			deviceChannelValidation(messageJson.get(deviceChannelString).asText(), listError);
			message.setMessageCategory(messageJson.get(deviceChannelString).asText());			
		}
		if (!listError.isEmpty()) {
			throw new ValidationException(listError);
		}
		logger.info("Mensagem pronta");
		return message;
	}

	private void messageTypeValidation(String messageType, List<ErrorMessageInvalid> validation) {
		if (Pattern.matches("[ARP]{1}[R]{1}[e]{1}[qs]{1}", messageType))
			return;
		if (Pattern.matches("Erro", messageType))
			return;
		validation.add(new ErrorMessageInvalid(messageTypeString));
	}

	private void messageCategoryValidation(String messageCategory, List<ErrorMessageInvalid> validation) {
		if (Pattern.matches("[0]{1}[1-2]{1}", messageCategory))
			return;
		validation.add(new ErrorMessageInvalid(messageCategoryString));
	}

	private void deviceChannelValidation(String deviceChannel, List<ErrorMessageInvalid> validation) {
		if (Pattern.matches("[0]{1}[1-3]{1}", deviceChannel))
			return;
		validation.add(new ErrorMessageInvalid(deviceChannelString));
	}

}
