package com.luxtavern.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.luxtavern.service.EmailService;


@Service
public class EmailServiceIMPL implements EmailService {
	

@Autowired
JavaMailSender javaMailSender;

	@Override
	public String sendOtp(String email, String subject, String body) {
	SimpleMailMessage msg=new SimpleMailMessage();
	msg.setTo(email);;
	msg.setSubject(subject);
	msg.setText(body);
	try {
		javaMailSender.send(msg);
	} catch (Exception e) {
e.printStackTrace();	}
	
		return null;
	}



}
