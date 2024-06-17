package com.luxtavern.model;

public class AuthResponseDTO {
	private String accessToken;
	private String tokenType="Bearer";
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public AuthResponseDTO(String accessToken) {
		super();
		this.accessToken = accessToken;
		
	}

	public AuthResponseDTO(String accessToken, String tokenType) {
		super();
		this.accessToken = accessToken;
		this.tokenType = tokenType;
	}
	public AuthResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
