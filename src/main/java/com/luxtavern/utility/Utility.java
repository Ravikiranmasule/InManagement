package com.luxtavern.utility;

import java.util.Random;

import org.springframework.stereotype.Component;


@Component
public class Utility {
	
	public String generateOtp() {
		Random random=new Random();
		int opt=100000+random.nextInt(99999);
		
		
		return String.valueOf(opt);
	}

}
