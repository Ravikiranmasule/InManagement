package com.luxtavern.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;


@Entity
public class User implements Serializable {
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	
    @NotNull
	@Column(nullable=false)
	private String userName;
    
    @NotNull
    @Email
	@Column(nullable=false)
	private String userEmail;
    
    @NotNull
    @Size(min=6, message="password size must be min 6 up to max=15")
	@Column(nullable=false)
	private String userPassWord;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long userId, String userName, String userEmail, String userPassWord) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassWord = userPassWord;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassWord() {
		return userPassWord;
	}

	public void setUserPassWord(String userPassWord) {
		this.userPassWord = userPassWord;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userPassWord="
				+ userPassWord + "]";
	}
	

	
	
	

}
