package com.luxtavern.serviceimpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.luxtavern.dao.CategoryRepository;
import com.luxtavern.entity.Category;
import com.luxtavern.service.CategoryService;
import com.luxtavern.utility.Helper;

@Service
public class CategoryServiceIMPL implements CategoryService{
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public ByteArrayInputStream getActualData() throws IOException {
		List<Category> all = categoryRepository.findAll();
		ByteArrayInputStream byteArrayInputStream = Helper.datatoExcel(all);
		
		return byteArrayInputStream;
	}

	@Override
	public Category saveCategory(Category category) {
		 Category savedCategory=null;
		if(!categoryRepository.existsByCategoryImage(category.getCategoryImage())) 
		{
	 savedCategory = categoryRepository.save(category);	
	 }
		 return   savedCategory;
	}
     
	@Override
	public Optional<Category> getCategoryById(Integer categoryId) {
		
		return categoryRepository.findById(categoryId);
	}

}
