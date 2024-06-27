package com.luxtavern.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luxtavern.entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	public List<Category> findAll();
	public Category save(Category category);
	public Optional<Category> findByCategoryId(Integer categoryId);
	public boolean existsByCategoryImage(String categoryImage);

}
