package com.luxtavern.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luxtavern.entity.Role;

public interface RoleRepository  extends JpaRepository<Role,Long>{
	
	Role save(Role role);
	
	Boolean existsByRoleName(String role);
	Role findByRoleName(String roleName);

}
