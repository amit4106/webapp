package com.cms.exception;

public class TrainerNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1582060296427734676L;

	public TrainerNotFoundException() {
		super();
	}
	
	public TrainerNotFoundException(String message) {
		super(message);
	}
}
