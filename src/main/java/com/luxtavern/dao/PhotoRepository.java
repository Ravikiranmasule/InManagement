package com.luxtavern.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luxtavern.entity.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
	
	public Photo save(Photo photo);

	public Photo findByPhotoName(String photoName);
	public Boolean existsByImage(byte[] image);
	

@Query("SELECT p.image FROM Photo p")
	public List<byte[]> findAllPhotoImage();

}
