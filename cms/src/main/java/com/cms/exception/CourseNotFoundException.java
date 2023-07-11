package com.cms.exception;

public class CourseNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4941540503776771117L;

	public CourseNotFoundException() {
		super();
	}
	
	public CourseNotFoundException(String message) {
		super(message);
	}
	
}
