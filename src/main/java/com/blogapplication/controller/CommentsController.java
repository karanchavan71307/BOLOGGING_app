package com.blogapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.paylaod.ApiResponse;
import com.blogapplication.paylaod.CommentsDto;
import com.blogapplication.service.CommentsService;

@RestController
@RequestMapping("/api/")
public class CommentsController {

	@Autowired
	private CommentsService commentsService;
	
	// for create the comments controller
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentsDto> createComments(@RequestBody CommentsDto commentsDto,@PathVariable("postId")int postId){
		
		CommentsDto createComments = this.commentsService.createComments(commentsDto, postId);
		
		return new ResponseEntity<CommentsDto>(createComments,HttpStatus.CREATED);
	}
	
	// delete comments controller
	
	@DeleteMapping("/comments/{commentsId}")
	public ResponseEntity<ApiResponse> deleteComments(@PathVariable("commentsId")int id){
		
		this.commentsService.deleteComments(id);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("comments deleted successfully !!",true),HttpStatus.OK);
		
	}
	
	
	
}
