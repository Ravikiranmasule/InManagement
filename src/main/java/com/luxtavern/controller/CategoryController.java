package com.luxtavern.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.luxtavern.entity.Category;
import com.luxtavern.service.CategoryService;

@RequestMapping("/api/category")
@RestController
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	public static String uploadDirectory=System.getProperty("user.dir")+"/src/main/webapp/images";
	
	@RequestMapping("/exel")
	public ResponseEntity<Resource>download() throws IOException{
		String fileName="categories.xlsx";
		ByteArrayInputStream actualData = categoryService.getActualData();
		InputStreamResource file=new InputStreamResource(actualData);
		
		
		return ResponseEntity
		.ok()
		.header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename="+fileName)
		.contentType(MediaType.parseMediaType("application/vnd.ms-exel"))
		.body(file);
		}

	@PostMapping("/saveCategory")
	public Category saveCategory(@ModelAttribute Category category,@RequestParam("file") MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		Path fileNameAndPath=Paths.get(uploadDirectory,fileName);
		category.setCategoryImage(fileName);
		Files.write(fileNameAndPath, file.getBytes());
		return categoryService.saveCategory(category);
		}
	
	@GetMapping("/getCategoryById/{categoryId}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Integer categoryId){
		Optional<Category> categoryById = categoryService.getCategoryById(categoryId);
		Category category=categoryById.get();
	
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}
	
	@GetMapping("/getCategoryImage/{categoryId}")
	public ResponseEntity<Resource> getCategoryImage(@PathVariable Integer categoryId) throws IOException{
		Optional<Category> category = categoryService.getCategoryById(categoryId);
		Category categoryObj = category.get();
		Path imagePath=Paths.get(uploadDirectory,categoryObj.getCategoryImage());
		Resource resource=new FileSystemResource(imagePath.toFile());
		String contentType=Files.probeContentType(imagePath);
		return ResponseEntity
				.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.body(resource);}
}
