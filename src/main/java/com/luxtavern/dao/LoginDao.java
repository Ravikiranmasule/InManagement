package com.luxtavern.dao;

import java.util.List;

import com.luxtavern.entity.User;

public interface LoginDao {
	public User login(String userEmail,String userPassWord);

	public User getUserById(Long userId);

	public String register(User user);
	public Boolean userExists(String userEmail);

	public List<User> getAllUser();

}
