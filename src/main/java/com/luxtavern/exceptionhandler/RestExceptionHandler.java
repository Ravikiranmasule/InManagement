package com.luxtavern.exceptionhandler;


import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.PropertyValueException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.luxtavern.Exception.EmailIsInvalidException;
import com.luxtavern.Exception.FailedToSaveRoleException;
import com.luxtavern.Exception.InvalidCredentialsException;
import com.luxtavern.Exception.PassWordIsInvalidException;
import com.luxtavern.Exception.RoleAlreadyPresentException;
import com.luxtavern.Exception.UserNotExistException;
import com.luxtavern.Exception.UserNotFoundException;
import com.luxtavern.model.ApiError;
import com.mysql.cj.x.protobuf.Mysqlx.Error;

@ControllerAdvice
public class RestExceptionHandler {
	
	

	@ExceptionHandler(value=UserNotExistException.class)
	public ResponseEntity<ApiError> userNotExistException(){
		ApiError error=new ApiError(101,"User does not exist",new Date());
		return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value=UserNotFoundException.class)
	public ResponseEntity<ApiError> userNotFoundException(){
		ApiError error =new ApiError(102,"User not found",new Date());
		return new ResponseEntity<ApiError>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=InvalidCredentialsException.class)
	public ResponseEntity<ApiError> userInvalidCredentialsException(){
		ApiError error =new ApiError(103,"Invalid Credentials",new Date());
		return new ResponseEntity<ApiError>(error,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value=EmailIsInvalidException.class)
	public ResponseEntity<ApiError> userEmailIsInvalidException(){
		ApiError error =new ApiError(104,"Email Is Invalid",new Date());
		return new ResponseEntity<ApiError>(error,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value=PassWordIsInvalidException.class)
	public ResponseEntity<ApiError> userPassWordIsInvalidException(){
		ApiError error =new ApiError(105,"PassWord Is Invalid",new Date());
		return new ResponseEntity<ApiError>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> userPropertyValueException(MethodArgumentNotValidException ex){
		Map<String,String> errors=new LinkedHashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
		String fieldName=((FieldError)error).getField();
		String errorMessage=error.getDefaultMessage();
		errors.put(fieldName,errorMessage);
		});
//		String errorMessage=errors.entrySet().stream().map(entry->entry.getKey()+":"
//		+entry.getValue()).collect(Collectors.joining(","));
		 StringBuilder errorMessage = new StringBuilder("Enter valid input:\n");
	        int count = 1;
	        for (Map.Entry<String, String> entry : errors.entrySet()) {
	            errorMessage.append(count).append(". ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
	            count++;
	        }
		
		ApiError error =new ApiError(106,errorMessage.toString(),new Date());
		return new ResponseEntity<ApiError>(error,HttpStatus.BAD_REQUEST);
	}
	
	 @ExceptionHandler(value = ConstraintViolationException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException ex) {
	        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

	        Map<String, String> errors = new LinkedHashMap<>();
	        int count = 1;
	        for (ConstraintViolation<?> violation : violations) {
	            errors.put(String.valueOf(count), violation.getPropertyPath() + ": " + violation.getMessage());
	            count++;
	        }

	        StringBuilder errorMessage = new StringBuilder("Validation failed for entity during persist:\n");
	        errors.forEach((key, value) -> errorMessage.append(key).append(". ").append(value).append("\n"));

	        ApiError error = new ApiError(107, errorMessage.toString(), new Date());
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }
	 
	 @ExceptionHandler(value=FailedToSaveRoleException.class)
	 public ResponseEntity<ApiError> failedToSaveRoleException(){
		 ApiError error=new ApiError(108," Failed To Save Role",new Date());
		 return new ResponseEntity<ApiError>(error,HttpStatus.FAILED_DEPENDENCY);
		 
	 }
	 @ExceptionHandler(value=RoleAlreadyPresentException.class)
	 public ResponseEntity<ApiError> RoleAlreadyPresentException(){
		 ApiError error=new ApiError(109," Role Already Present",new Date());
		 return new ResponseEntity<ApiError>(error,HttpStatus.FAILED_DEPENDENCY);
		 
	 }
}
