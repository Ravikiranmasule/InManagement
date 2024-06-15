package com.luxtavern.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luxtavern.entity.User;

@Repository
public interface LoginDao extends JpaRepository<User,Long> {
	public User findByUserEmailAndUserPassWord(String userEmail,String userPassWord);

	public Optional<User> findById(Long userId);

	public User save(User user);
	public User findByUserEmail(String email);

	public List<User> findAll();

}
