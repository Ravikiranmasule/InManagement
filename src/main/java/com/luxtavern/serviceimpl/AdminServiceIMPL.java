package com.luxtavern.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxtavern.dao.RoleRepository;
import com.luxtavern.dao.UserRepository;
import com.luxtavern.entity.Role;
import com.luxtavern.entity.UserEntity;
import com.luxtavern.service.AdminService;

@Service
public class AdminServiceIMPL implements AdminService{
	
	private static final Logger logger=LoggerFactory.getLogger(AdminServiceIMPL.class);

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
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


	
	
	@Override
	public Optional<UserEntity> getUserById(Long userId) {
		logger.error("user is being fetch through id");

		return userRepository.findById(userId);
		
	}



}
