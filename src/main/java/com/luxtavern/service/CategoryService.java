package com.luxtavern.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.luxtavern.entity.Category;

public interface CategoryService {
	public ByteArrayInputStream getActualData() throws IOException;

	public Category saveCategory(Category category);

	public Optional<Category> getCategoryById(Integer categoryId);

}
