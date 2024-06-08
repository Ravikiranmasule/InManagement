package com.luxtavern.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luxtavern.entity.User;
import com.luxtavern.service.LoginService;

@RestController
public class HomePageController {
   
	@Autowired
	LoginService loginService;
	
	@GetMapping("/")
	public ResponseEntity<String> getHomePage() {
		return new ResponseEntity<String>("Welcome to LuxTavern",HttpStatus.OK);
	}
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user){
		String msg=loginService.register(user);
		if(msg.equals("conflict")) {
			msg="user already exist";
			return new ResponseEntity<String>(msg,HttpStatus.CONFLICT);
		}
		else if(msg.equals("success")) {
			msg="user added successfully";
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);}
		else {
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
public User getUserById(@PathVariable Long userId) 
	{
		
		
	return loginService.getUserById(userId);

}
	@GetMapping("/getalluser")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> list=loginService.getAllUser();
		return new  ResponseEntity<List<User> >(list,HttpStatus.FOUND);
	}
}
