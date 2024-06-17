package com.luxtavern.daoimpl;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.luxtavern.dao.UserRepository;
import com.luxtavern.entity.UserEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
class LoginDaoTest {

@Autowired
UserRepository userRepository;

@Autowired
TestEntityManager testEntityManager;

//@BeforeEach
//void setUp() {
//	User user=new User(101L,"karn@gmail.com","karn@123");
//	testEntityManager.persist(user);
//	testEntityManager.flush();
//}

//@Test
//public void testGetUserById() {
//	User user=loginDao.getUserById(101L);
//	assertEquals("karn@gmail.com", user.getUserEmail());
//}

}
