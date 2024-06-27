package com.luxtavern.serviceimpl;

import java.io.IOException;
import java.util.List;

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
		String msg=null;
		Photo photo=new Photo();
		photo.setPhotoName(file.getOriginalFilename());
		photo.setContentType(file.getContentType());
		photo.setImage(file.getBytes());
		if(photoRepository.existsByImage(file.getBytes())) {
			return null;
		}else {
		return photoRepository.save(photo);}
		
	}

	@Override
	public Photo findByPhotoName(String photoName) {
		Photo photoFromRepo = photoRepository.findByPhotoName(photoName);
		
		return photoFromRepo;
	}

	@Override
	public List<byte[]> getAllImage() {
	
		return photoRepository.findAllPhotoImage();
	}

}
