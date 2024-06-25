package com.luxtavern.serviceimpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.luxtavern.dao.PhotoRepository;
import com.luxtavern.entity.Photo;
@Service
public class PhotoServiceIMPL implements com.luxtavern.service.PhotoService {
	
	@Autowired
	PhotoRepository photoRepository;
	
	@Override
	public Photo uploadPhoto(MultipartFile file) throws IOException {
		Photo photo=new Photo();
		photo.setPhotoName(file.getOriginalFilename());
		photo.setData(file.getBytes());
		return photoRepository.save(photo);
		
	}

}
