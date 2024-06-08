package com.luxtavern.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxtavern.Exception.EmailIdInvalid;
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

	@Override
	public String login(String userEmail,String userPassWord) {
	String msg=null;
	User user1=loginDao.login(userEmail,userPassWord);
	try {
	if(user1.getUserPassWord().equals(userPassWord)) {
		msg="valid user";
	}}
	catch (Exception e) {
		throw new UserNotExist(" user not exist in database");
	}
//		if(user.getUserEmail()==null || user.getUserEmail().isEmpty()) {
//			throw new EmailIdInvalid("email is invalid");
//			
//		}
//		else if(user.getUserPassWord()==null || user.getUserPassWord().isEmpty())  {
//			throw new PassWordIsInvalid("Password is invalid");
//			
//		}
//		User user1=loginDao.login(user);
//		if(user1==null) {
//			throw new InvalidCredentials(" Invalid Credentials");
//		}
//		
//			else {
//				 msg=" you are successfully logged in";
//				 
//			}
//		
		return msg;
	}

	@Override
	public User getUserById(Long userId) {
		
		return loginDao.getUserById(userId);
		
	}

	@Override
	public String register(User user) {
		String msg;
		if(!loginDao.userExists(user.getUserEmail())) {
		msg=loginDao.register(user);}
		else {
			msg="conflict";
		}
		return msg;
	}

	@Override
	public List<User> getAllUser() {
List<User> list=loginDao.getAllUser();		return list;
	}
	
}
