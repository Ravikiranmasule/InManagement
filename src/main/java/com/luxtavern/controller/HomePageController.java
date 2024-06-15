package com.luxtavern.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luxtavern.Exception.UserNotFound;
import com.luxtavern.entity.User;
import com.luxtavern.service.LoginService;

@RestController
@Validated
public class HomePageController {
   
	@Autowired
	LoginService loginService;
	
	private static Logger logger=LoggerFactory.getLogger(HomePageController.class);
	
	@GetMapping("/")
	public ResponseEntity<String> getHomePage() {
		return new ResponseEntity<String>("Welcome to LuxTavern",HttpStatus.OK);
	}
	@PostMapping("/register")
	public ResponseEntity<String> register( @Valid @RequestBody User user){
		String msg=loginService.register(user);
		if(msg.equals("conflict")) {
			msg="user already exist";
			logger.warn("user already exists");
			return new ResponseEntity<String>(msg,HttpStatus.CONFLICT);
		}
		else if(msg.equals("ok")) {
			logger.info("user added successfully");			
			msg="user added successfully";
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);}
		else {
			logger.error("failed to add user");
			msg="failed to add user";
			return new ResponseEntity<String>(msg,HttpStatus.FAILED_DEPENDENCY);
		}
	}
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam String userEmail, String userPassWord) {
		String msg=loginService.login(userEmail,userPassWord);
		System.out.println("msg is "+msg);
		if(msg==null) {
			msg="invalid user";
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		}
		else {
			return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
		}
		
		
	}
	
	@GetMapping("/userbyid/{userId}")
public Optional<User> getUserById(@PathVariable Long userId) 
	{
		Optional<User> user=loginService.getUserById(userId);
		if(!user.isPresent()) {
			System.out.println("null user");
			throw new UserNotFound("user not found");
		}else {
		return user;
		
		}
	

}
	@GetMapping("/getalluser")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> list=loginService.getAllUser();
		return new  ResponseEntity<List<User> >(list,HttpStatus.FOUND);
	}
	
	
}
