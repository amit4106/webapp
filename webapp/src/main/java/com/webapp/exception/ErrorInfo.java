package com.webapp.exception;

import lombok.Data;

@Data
public class ErrorInfo {

	private String message;
	private Integer errorCode;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
}
