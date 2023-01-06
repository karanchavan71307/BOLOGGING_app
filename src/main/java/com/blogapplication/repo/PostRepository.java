package com.blogapplication.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blogapplication.model.Category;
import com.blogapplication.model.Post;
import com.blogapplication.model.User;
import com.blogapplication.paylaod.PostDto;

public interface PostRepository extends JpaRepository<Post, Integer>{

	
	List<Post> findByCategory(Category category);
	List<Post> findByUser(User user);
	
	List<PostDto> findByTitleContaining(String keyword);
}
