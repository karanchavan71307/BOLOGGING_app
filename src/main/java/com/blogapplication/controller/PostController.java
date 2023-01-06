package com.blogapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.paylaod.ConstantKeyword;
import com.blogapplication.paylaod.PostDto;
import com.blogapplication.paylaod.PostResponse;
import com.blogapplication.serviceimpl.PostServiceImpl;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostServiceImpl postServiceImpl;

	// save the post 
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable("userId") Integer userid,
			@PathVariable("categoryId") Integer categoryId

	) {

		PostDto createPost = this.postServiceImpl.createPost(postDto, userid, categoryId);

		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

	}

	/// delete post
	@DeleteMapping("/posts/{id}")
	public ResponseEntity<?> deletePost(@PathVariable("id") int id) {

		this.postServiceImpl.deletePost(id);

		return ResponseEntity.status(HttpStatus.OK).body("post deleted succesfully !!");

	}

	// update the post

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable("postId") int id) {

		PostDto updatePost = this.postServiceImpl.updatePost(postDto, id);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}

	// get single post
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getSiglePost(@PathVariable("postId") int id) {

		PostDto signlePost = this.postServiceImpl.getSignlePost(id);
		
		System.out.println(signlePost);
		

		return new ResponseEntity<PostDto>(signlePost, HttpStatus.OK);

	}
	
	
	// get all post 
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber",defaultValue=ConstantKeyword.PAGE_NUMBER,required = false) int pageNumber,
			@RequestParam(value = "pageSize",defaultValue = ConstantKeyword.PAGE_SIZE,required = false) int pageSize,
			@RequestParam(value = "sortBy",defaultValue = ConstantKeyword.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = ConstantKeyword.SORT_DIR,required = false) String sortDir
			){							
		PostResponse postResponse = this.postServiceImpl.getAllPost(pageNumber, pageSize,sortBy,sortDir);								
			return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
			
	}
	
	// find by categoryId
	
	@GetMapping("/posts/category/{categoryId}")
	public ResponseEntity<List<PostDto>> getByCategory(@PathVariable("categoryId")int id){
		
		List<PostDto> list = this.postServiceImpl.getPostByCategory(id);
		
		return new ResponseEntity<List<PostDto>>(list,HttpStatus.OK);
	}
	
	// get by UserId
	@GetMapping("/posts/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable("userId")int id){
		
		List<PostDto> list = this.postServiceImpl.getPostByUser(id);
		
		return new ResponseEntity<List<PostDto>>(list,HttpStatus.OK);
		
	}
	
	/// serach facility
	
	@GetMapping("/posts/serach/{title}")
	public ResponseEntity<List<PostDto>> serachfacility(@PathVariable("title")String title){
		
		List<PostDto> list = this.postServiceImpl.SerarchPost(title);
		return new ResponseEntity<List<PostDto>>(list,HttpStatus.OK);
	}	
}
