package com.luxtavern.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luxtavern.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
	public UserEntity findByUserEmailAndUserPassWord(String userEmail,String userPassWord);

	public Optional<UserEntity> findById(Long userId);

	public UserEntity save(UserEntity user);
	public UserEntity findByUserEmail(String email);

	public List<UserEntity> findAll();
	public Optional<UserEntity> findByUserName(String userName);

}
