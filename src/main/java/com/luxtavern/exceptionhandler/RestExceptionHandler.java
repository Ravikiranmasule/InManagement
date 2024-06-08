package com.luxtavern.exceptionhandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.luxtavern.Exception.UserNotExist;
import com.luxtavern.model.ApiError;

@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(value=UserNotExist.class)
	public ResponseEntity<ApiError> userNotExistException(){
		ApiError error=new ApiError(101,"User does not exist",new Date());
		return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
	}

}
