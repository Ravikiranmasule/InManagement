package com.luxtavern.model;

public class UserDTO {
	
		private Long userId;
		
		private String userName;
		
		private String userEmail;
		
		private String userPassWord;

		public UserDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

		public UserDTO(Long userId, String userName, String userEmail, String userPassWord) {
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
			return "UserDTO [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail
					+ ", userPassWord=" + userPassWord + "]";
		}

		

	}


