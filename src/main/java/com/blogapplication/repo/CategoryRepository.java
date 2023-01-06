package com.blogapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	
}
