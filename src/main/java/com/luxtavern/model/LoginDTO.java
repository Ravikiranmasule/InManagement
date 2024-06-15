package com.luxtavern.model;

public class LoginDTO {
	
	private String loginEmail;
	private String loginPassword;
	public String getLoginEmail() {
		return loginEmail;
	}
	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public LoginDTO(String loginEmail, String loginPassword) {
		super();
		this.loginEmail = loginEmail;
		this.loginPassword = loginPassword;
	}
	public LoginDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "LoginDTO [loginEmail=" + loginEmail + ", loginPassword=" + loginPassword + "]";
	}

}
