package com.luxtavern.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.luxtavern.Exception.EmailIsInvalid;
import com.luxtavern.Exception.InvalidCredentials;
import com.luxtavern.Exception.PassWordIsInvalid;
import com.luxtavern.Exception.UserNotExist;
import com.luxtavern.dao.LoginDao;
import com.luxtavern.entity.User;
import com.luxtavern.service.LoginService;

@Service
public class LoginServiceIMPL implements LoginService {
	
	@Autowired
	LoginDao loginDao;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public String login(String userEmail,String userPassWord) {
	String msg=null;
	

	if(userEmail==null || userEmail.isEmpty()) {
		throw new EmailIsInvalid("email is invalid");
		
	}
	else if(userPassWord==null || userPassWord.isEmpty())  {
		throw new PassWordIsInvalid("Password is invalid");
		
	}
	User user1=loginDao.findByUserEmail(userEmail);
	System.out.println(user1);
	
	if(user1!=null)
		
	
		{if(user1.getUserEmail().equals(userEmail)) {
			 if(passwordEncoder.matches(userPassWord, user1.getUserPassWord())) {
					msg="valid user";
				}
			 else {
				 throw new PassWordIsInvalid("Password is invalid");
			 }
		}else {
			throw new EmailIsInvalid("email is invalid");
		}}
	else {
		throw new InvalidCredentials("invalid credentials");
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
	public Optional<User> getUserById(Long userId) {
		
		return loginDao.findById(userId);
		
	}
	

	@Override
	public String register(User user) {
		String msg;
		String emailFromUser=user.getUserEmail();
		User user1=loginDao.findByUserEmail(emailFromUser);
		String encodedPassword= passwordEncoder.encode(user.getUserPassWord());
		user.setUserPassWord(encodedPassword);
		if(user1==null) {
		User user2=loginDao.save(user);
		msg="ok";}
		else {
			msg="conflict";
		}
		return msg;
	}

	@Override
	public List<User> getAllUser() {
List<User> list=loginDao.findAll();		
return list;
	}
	
}
