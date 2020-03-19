package com.wipro.api.model;

public class ErrorMessageInvalid {

	private String errorMessage;

	public ErrorMessageInvalid(String errorMessage) {
		this.errorMessage = errorMessage+ " Invalid";
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

}
