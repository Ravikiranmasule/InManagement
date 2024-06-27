package com.luxtavern.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.luxtavern.entity.Photo;

public interface PhotoService {
	public Photo uploadPhoto(MultipartFile file) throws IOException;

	public Photo findByPhotoName(String photoName);

	public List<byte[]> getAllImage();
}
