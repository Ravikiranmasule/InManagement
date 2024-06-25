package com.luxtavern.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.luxtavern.entity.Role;
import com.luxtavern.entity.UserEntity;

public interface AdminService {
public List<UserEntity> getAllUser();
	
	public String saveRole(Role role);
	
	
	public Optional<UserEntity> getUserById(Long userId);


}
