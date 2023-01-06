package com.blogapplication.service;

import java.util.List;
import java.util.Optional;

import com.blogapplication.paylaod.PostDto;
import com.blogapplication.paylaod.PostResponse;

public interface PostService {

	// create post
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
//	update post
	
	PostDto updatePost(PostDto postDto,int postId);
	
	// delete post
	
	void deletePost(Integer postId);
	
	// get all post
	
	PostResponse getAllPost(int pageNumber,int pageSize,String sortBy,String sortDir);
	
	// get single post\
	
	PostDto getSignlePost(int postId);
	
	List<PostDto> getPostByCategory(Integer  categoryId);
	
	List<PostDto> getPostByUser(Integer userId);
	
	// serach
	
	

	List<PostDto> SerarchPost(String keyword);
	
}
