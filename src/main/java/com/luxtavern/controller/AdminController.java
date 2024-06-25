package com.luxtavern.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.luxtavern.entity.Role;
import com.luxtavern.entity.UserEntity;
import com.luxtavern.service.AdminService;

@RequestMapping("/api/admin")
@RestController
public class AdminController {
	Logger logger=LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	AdminService adminService;

	@GetMapping("/userbyid/{userId}")
	public Optional<UserEntity> getUserById(@PathVariable Long userId) 
		{
			Optional<UserEntity> user=adminService.getUserById(userId);
			if(!user.isPresent()) {
				System.out.println("null user");
				throw new UserNotFoundException("user not found");
			}else {
			return user;
			
			}
		

	}
		@GetMapping("/getalluser")
		public ResponseEntity<List<UserEntity>> getAllUser(){
			List<UserEntity> list=adminService.getAllUser();
			return new  ResponseEntity<List<UserEntity> >(list,HttpStatus.FOUND);
		}
		
		@PostMapping("/saveRole")
		public ResponseEntity<String> saveRole(@RequestBody Role role) {
			String msg1=null;
			String msg=adminService.saveRole(role);
			if(msg.equals("fail")) {
				throw new FailedToSaveRoleException("failed to save role");
				
			}else if(msg.equals("conflict")) {
				throw new RoleAlreadyPresentException(" role already present in database");
			}
			else {
				msg1="Role successfully saved";
				return new ResponseEntity<String>(msg1,HttpStatus.CREATED);
			}
			
		}
		
}
