package com.luxtavern.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luxtavern.Exception.FailedToSaveRoleException;
import com.luxtavern.Exception.RoleAlreadyPresentException;
import com.luxtavern.Exception.UserNotFoundException;
import com.luxtavern.dao.RoleRepository;
import com.luxtavern.entity.Role;
import com.luxtavern.entity.UserEntity;
import com.luxtavern.model.AuthResponseDTO;
import com.luxtavern.security.JWTGenerator;
import com.luxtavern.service.LoginService;

@RestController
@Validated
@RequestMapping("/api/auth")
public class AuthController {
   
	@Autowired
	private LoginService loginService;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JWTGenerator jwtGenerator;
	
	
	private static Logger logger=LoggerFactory.getLogger(AuthController.class);
	
	
	@PostMapping("/register")
	public ResponseEntity<String> register( @Valid @RequestBody UserEntity user){
		
		Role roles=roleRepository.findByRoleName("USER");
		user.setRoles(Collections.singletonList(roles));
		String msg=loginService.register(user);
		if(msg.equals("conflict")) {
			msg="user already exist";
			logger.debug("debugging luxtavern controller");
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
	public ResponseEntity<AuthResponseDTO> login(@RequestParam String userName, String userPassWord) {
		Authentication authentication=authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassWord));
		SecurityContextHolder.getContext().setAuthentication(authentication);
//		String msg=loginService.login(userEmail,userPassWord);
//		System.out.println("msg is "+msg);
//		if(msg==null) {
//			msg="invalid user";
//			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
//		}
//		else {
//			return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
//		}
		String token=jwtGenerator.generateToken(authentication);
		return new ResponseEntity<AuthResponseDTO>(new AuthResponseDTO(token), HttpStatus.OK);
	}
	
	
}
