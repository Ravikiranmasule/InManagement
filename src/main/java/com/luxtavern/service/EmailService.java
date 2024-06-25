package com.luxtavern.service;

import org.springframework.stereotype.Service;


public interface EmailService {
	public String sendOtp(String email,String subject,String body);
}
