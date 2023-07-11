package com.cms.exception;

public class ErrorInfo {

	private String message;
	private Integer errorCode;

	public ErrorInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorInfo(String message, Integer errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}

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

	@Override
	public String toString() {
		return "ErrorInfo [message=" + message + ", errorCode=" + errorCode + "]";
	}

}
