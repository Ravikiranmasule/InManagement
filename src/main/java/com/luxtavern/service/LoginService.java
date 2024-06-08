package com.luxtavern.service;

import java.util.List;

import com.luxtavern.entity.User;


public interface LoginService {

	public String login(String userEmail,String userPassWord);

	public User getUserById(Long userId);

	public String register(User user);

	public List<User> getAllUser();

}
