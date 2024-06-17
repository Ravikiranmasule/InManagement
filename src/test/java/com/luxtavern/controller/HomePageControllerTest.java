package com.luxtavern.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.net.ssl.SSLEngineResult.Status;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxtavern.dao.UserRepository;
import com.luxtavern.entity.UserEntity;
import com.luxtavern.service.LoginService;

@WebMvcTest(AuthController.class)
class AuthControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	LoginService loginService;
	UserRepository userRepository;
	SessionFactory sf;
	
	

	private UserEntity user;
	
//	@BeforeEach
//	void setUp() {
//		user=new User(101L,"aniket@gmail.com","aniket@123");
//		Mockito.when(loginService.register(user)).thenReturn(user.toString());
//	}
	
//	@Test
//	void testRegister() throws Exception {
//		Mockito.when(loginService.register(user)).thenReturn(user.toString());
//		mockMvc.perform(MockMvcRequestBuilders.post("/register")
//		.contentType(MediaType.APPLICATION_JSON)
//		.content( "{\"userId\":101, \"userEmail\":\"aniket@gmail.com\", \"userPassWord\":\"aniket@123\"}"))
//		.andExpect(MockMvcResultMatchers.status().isCreated());
//
//	
//	}
	
	@Test
	void testGetHomePage()throws Exception {
		RequestBuilder request=MockMvcRequestBuilders
				.get("/")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("Welcome to LuxTavern"))
				.andReturn();
		
	}
}
