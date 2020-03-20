package com.wipro.api.model;

import com.google.gson.JsonObject;

public class Message {

	private String messageType;
	private String deviceChannel;
	private String messageCategory;

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getDeviceChannel() {
		return deviceChannel;
	}

	public void setDeviceChannel(String deviceChannel) {
		this.deviceChannel = deviceChannel;
	}

	public String getMessageCategory() {
		return messageCategory;
	}

	public void setMessageCategory(String messageCategory) {
		this.messageCategory = messageCategory;
	}

	@Override
	public String toString() {
		JsonObject message = new JsonObject();
		if (messageType != null) {
			message.addProperty("messageType", messageType);
		}
		if (deviceChannel != null) {
			message.addProperty("deviceChannel", deviceChannel);
		}
		if (messageCategory != null) {
			message.addProperty("messageCategory", messageCategory);
		}
		return message.toString();
	}
}
