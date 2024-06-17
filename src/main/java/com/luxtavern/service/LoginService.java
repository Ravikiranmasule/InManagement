package com.luxtavern.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.luxtavern.entity.Role;
import com.luxtavern.entity.UserEntity;


public interface LoginService {

	public String login(String userEmail,String userPassWord);

	public Optional<UserEntity> getUserById(Long userId);

	public String register(UserEntity user);

	public List<UserEntity> getAllUser();
	
	public String saveRole(Role role);
	

}
