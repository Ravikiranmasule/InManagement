package com.luxtavern.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.luxtavern.Exception.EmailIsInvalidException;
import com.luxtavern.Exception.InvalidCredentialsException;
import com.luxtavern.Exception.PassWordIsInvalidException;
import com.luxtavern.Exception.UserNotExistException;
import com.luxtavern.dao.RoleRepository;
import com.luxtavern.dao.UserRepository;
import com.luxtavern.entity.Role;
import com.luxtavern.entity.UserEntity;
import com.luxtavern.service.LoginService;

@Service
public class LoginServiceIMPL implements LoginService {
	
	private static final Logger logger=LoggerFactory.getLogger(LoginServiceIMPL.class);
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public String login(String userEmail,String userPassWord) {
	String msg=null;
	

	if(userEmail==null || userEmail.isEmpty()) {
		throw new EmailIsInvalidException("email is invalid");
		
	}
	else if(userPassWord==null || userPassWord.isEmpty())  {
		throw new PassWordIsInvalidException("Password is invalid");
		
	}
	UserEntity user1=userRepository.findByUserEmail(userEmail);
	System.out.println(user1);
	
	if(user1!=null)
		
	
		{if(user1.getUserEmail().equals(userEmail)) {
			 if(passwordEncoder.matches(userPassWord, user1.getUserPassWord())) {
					msg="valid user";
					logger.info("valid user");

				}
			 else {
				 logger.error("invalid password");

				 throw new PassWordIsInvalidException("Password is invalid");
			 }
		}else {
			logger.error("invalid email");

			throw new EmailIsInvalidException("email is invalid");
		}}
	else {
		logger.error("invalid credentials");
		throw new InvalidCredentialsException("invalid credentials");
	}
		
		
		return msg;
	 }
	
		
//		User user1=loginDao.login(user);
//		if(user1==null) {
//			throw new InvalidCredentials(" Invalid Credentials");
//		}
//		
//			else {
//				 msg=" you are successfully logged in";
//				 
//			}
		
		
	

	@Override
	public Optional<UserEntity> getUserById(Long userId) {
		logger.error("user is being fetch through id");

		return userRepository.findById(userId);
		
	}
	

	@Override
	public String register(UserEntity user) {
		String msg;
		String emailFromUser=user.getUserEmail();
		UserEntity user1=userRepository.findByUserEmail(emailFromUser);
		String encodedPassword= passwordEncoder.encode(user.getUserPassWord());
		user.setUserPassWord(encodedPassword);
		if(user1==null) {
		UserEntity user2=userRepository.save(user);
		logger.info("user is getting saved");

		msg="ok";}
		else {
			logger.warn("conflict of user happend");

			msg="conflict";
		}
		return msg;
	}

	@Override
	public List<UserEntity> getAllUser() {
List<UserEntity> list=userRepository.findAll();		
logger.info("returning list of user");

return list;
	}
	
	public String saveRole(Role role) {
		String msg;
		Role role1 = null;
		Boolean isRoleExist=roleRepository.existsByRoleName(role.getRoleName());
		if(!isRoleExist) {
		 role1=roleRepository.save(role);}
		else {
			msg="conflict";
		}
		if(role1==null) {
			msg="fail";
		//	throw new FailedToSaveRole(" failed to save role");
		}else {
			msg="success";
		}
		return msg;
	}
	
}
