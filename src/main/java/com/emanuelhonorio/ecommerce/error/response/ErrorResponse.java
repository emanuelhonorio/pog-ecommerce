package com.emanuelhonorio.ecommerce.error.response;

import java.io.Serializable;
import java.util.Map;

public class ErrorResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private int status;
	private String message;
	private Map<String, String> fieldErrors;

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(Map<String, String> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

}
