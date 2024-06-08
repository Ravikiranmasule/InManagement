package com.luxtavern.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.luxtavern.dao.LoginDao;
import com.luxtavern.entity.User;
import com.luxtavern.service.LoginService;

@SpringBootTest
class LoginServiceTest {

	@Autowired
	LoginService loginService;
	@MockBean
	LoginDao loginDao;
	
	@BeforeEach
	void setUp() {
		User user=new User(103L,"karn@gmail.com","karn@123");
		Mockito.when(loginDao.getUserById(101L)).thenReturn(user);
	}
	
	@Test
	public void testGetUserById() {
		String userEmail="karn@gmail.com";
		User user=loginService.getUserById(101L);
		assertEquals(userEmail, user.getUserEmail());
	}

}
