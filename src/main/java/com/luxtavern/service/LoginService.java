package com.luxtavern.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.luxtavern.entity.User;


public interface LoginService {

	public String login(String userEmail,String userPassWord);

	public Optional<User> getUserById(Long userId);

	public String register(User user);

	public List<User> getAllUser();
	

}
