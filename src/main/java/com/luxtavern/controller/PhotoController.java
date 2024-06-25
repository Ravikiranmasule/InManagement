package com.luxtavern.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Photo> uploadPhoto(@RequestParam("file") MultipartFile file){
		try {
			Photo uploadPhoto = photoService.uploadPhoto(file);
			return new ResponseEntity<Photo>(uploadPhoto,HttpStatus.ACCEPTED);
		} catch (IOException e) {
			
			e.printStackTrace();
			return new ResponseEntity<Photo>(HttpStatus.FAILED_DEPENDENCY);
		}
				
	}

}
