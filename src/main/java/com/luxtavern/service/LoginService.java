package com.luxtavern.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.luxtavern.entity.Photo;
import com.luxtavern.entity.Role;
import com.luxtavern.entity.UserEntity;


public interface LoginService {

	public String login(String userEmail,String userPassWord);

	
	public String register(UserEntity user);

	
	
	

}
