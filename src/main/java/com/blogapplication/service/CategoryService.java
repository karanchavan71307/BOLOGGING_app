package com.blogapplication.service;

import java.util.List;

import com.blogapplication.model.Category;
import com.blogapplication.paylaod.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto, int id);
	
	List<CategoryDto> getAllCategory();
	
	CategoryDto getCatogoryById(int id);
	
	void deleteCategory(int id);
}
