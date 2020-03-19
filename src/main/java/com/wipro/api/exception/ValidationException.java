package com.wipro.api.exception;

import java.util.List;
import com.wipro.api.model.ErrorMessageInvalid;

public class ValidationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ErrorMessageInvalid> list;

	public ValidationException(List<ErrorMessageInvalid> list) {
		this.list = list;
	}

	public List<ErrorMessageInvalid> getList() {
		return list;
	}

	public void setList(List<ErrorMessageInvalid> list) {
		this.list = list;
	}
}
