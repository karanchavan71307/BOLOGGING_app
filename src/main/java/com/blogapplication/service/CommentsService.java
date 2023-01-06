package com.blogapplication.service;

import com.blogapplication.paylaod.CommentsDto;

public interface CommentsService{
	
	
	// create comments
	CommentsDto createComments(CommentsDto commentsDto,Integer postId);
	
//	delete comments
	
	void deleteComments(Integer id);
}
