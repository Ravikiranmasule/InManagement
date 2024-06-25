package com.luxtavern.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luxtavern.service.EmailService;
import com.luxtavern.service.LoginService;
import com.luxtavern.utility.Utility;

@RequestMapping("/api/email")
@RestController
public class EmailController {
	Logger logger=LoggerFactory.getLogger(EmailController.class);
	@Autowired
	EmailService emailService;
	@Autowired
	Utility utility;
	
	@PostMapping("/sendEmail")
	public ResponseEntity<String> sendOtp(@RequestParam String email)
	{
		String otp=utility.generateOtp();
		logger.info(otp);
		String subject="your otp code";
		String body="your otp code is"+otp+"valid for 10 minutes";
		try {
			emailService.sendOtp(email, subject, body);
			return new ResponseEntity<String>(otp,HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(otp,HttpStatus.NO_CONTENT);
		}
		
		
	}

}
