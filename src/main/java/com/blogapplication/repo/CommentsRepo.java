package com.blogapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.model.Comments;

public interface CommentsRepo extends JpaRepository<Comments, Integer>{

	
	
	
}
