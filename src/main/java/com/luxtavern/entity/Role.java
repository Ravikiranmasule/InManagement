package com.luxtavern.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Role implements Serializable{
	
	@Id
	private Long roleId;
	
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String roleName;

	@ManyToMany(mappedBy ="roles")
	 @JsonIgnore
	 private List<UserEntity> users=new ArrayList<>();

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(Long roleId, @NotNull String roleName, List<UserEntity> users) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.users = users;
	}

}
