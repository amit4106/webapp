package com.cms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobleExceptionHandler {

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ErrorInfo> exceptionHandler1(Exception ex){
		ErrorInfo error=new ErrorInfo();
		error.setMessage(ex.getMessage());
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.CREATED);
	}
	
	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<ErrorInfo> exceptionHandler2(Exception ex){
		ErrorInfo error=new ErrorInfo();
		error.setMessage(ex.getMessage());
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.CREATED);
	}
	
	@ExceptionHandler(TrainerNotFoundException.class)
	public ResponseEntity<ErrorInfo> exceptionHandler3(Exception ex){
		ErrorInfo error=new ErrorInfo();
		error.setMessage(ex.getMessage());
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.CREATED);
	}
	
}
