package com.luxtavern.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	private static final Logger logger =LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping("/")
	public ResponseEntity<String> getHomePage() {
		logger.debug("debuggirn luxtavern");
		return new ResponseEntity<String>("Welcome to LuxTavern",HttpStatus.OK);
	}

}
