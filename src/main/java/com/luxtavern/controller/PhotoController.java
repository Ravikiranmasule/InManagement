package com.luxtavern.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.luxtavern.entity.Photo;
import com.luxtavern.service.LoginService;
import com.luxtavern.service.PhotoService;

@RequestMapping("/api/photo")
@RestController
public class PhotoController {
	public static final Logger logger=LoggerFactory.getLogger(PhotoController.class);
	@Autowired
	PhotoService photoService;
	
	
	@PostMapping("/uploadPhoto")
	public ResponseEntity<Photo> uploadPhoto(@RequestParam("file") MultipartFile file) throws IOException
	{
	
			logger.info("in upload method of photocontroller");
			Photo uploadPhoto = photoService.uploadPhoto(file);
			if(uploadPhoto!=null) {
			
			return new ResponseEntity<Photo>(uploadPhoto,HttpStatus.ACCEPTED);}
		
			else {
			logger.error("in error");
			return new ResponseEntity<Photo>(HttpStatus.FAILED_DEPENDENCY);
			}
				
	}
	
	@GetMapping("/getPhoto/{photoName}")
	public ResponseEntity<byte[]> findByPhotoName(@PathVariable String photoName)
	{
		try {
			logger.info("in findbyphotoname method of photocontroller");
			Photo photoFromRepo = photoService.findByPhotoName(photoName);
			HttpHeaders header=new HttpHeaders();
			//header.setContentType(MediaType.IMAGE_JPEG);
			header.setContentType(MediaType.parseMediaType(photoFromRepo.getContentType()));
			header.setContentLength(photoFromRepo.getImage().length);
			return new ResponseEntity<byte[]>(photoFromRepo.getImage(),header,HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<byte[]>(HttpStatus.EXPECTATION_FAILED);
		}
		
		
	}
	
	@GetMapping("/getAllImages")
	public ResponseEntity<List<Resource>> getAllImages(){
		List<byte []> allImages=photoService.getAllImage();
		HttpHeaders header=new HttpHeaders();
		header.setContentType(MediaType.IMAGE_JPEG);
		  return ResponseEntity
	                .ok()
	                .contentType(MediaType.IMAGE_JPEG)
	                .body(allImages.stream()
	                        .map(ByteArrayResource::new)
	                        .collect(Collectors.toList()));
		
	}

}
