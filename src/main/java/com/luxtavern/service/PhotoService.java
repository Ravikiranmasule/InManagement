package com.luxtavern.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.luxtavern.entity.Photo;

public interface PhotoService {
	public Photo uploadPhoto(MultipartFile file) throws IOException;
}
