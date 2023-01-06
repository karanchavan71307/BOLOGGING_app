package com.blogapplication.paylaod;

import java.util.List;

import lombok.Data;

@Data
public class PostResponse {

	private List<PostDto> content;
	
	private int pageNumber;
	
	private int pageSize;
	
	private long  totalElements;
	
	private int totalPage;
	
	private boolean lastPage;
	
	
	
	
}
