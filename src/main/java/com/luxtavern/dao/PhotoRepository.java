package com.luxtavern.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luxtavern.entity.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
	
	public Photo save(Photo photo);

}
