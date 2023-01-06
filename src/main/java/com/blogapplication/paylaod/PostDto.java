package com.blogapplication.paylaod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PostDto {
	
	private int id;
	
	@NotEmpty
	@Size(min = 3,max = 30,message = "title between 3-30 charector !!")
	private String title;
	
	@NotEmpty
	@Size(max = 200,message = "content max 200 charectors !!")
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto categoryDto;
	
	private UserDto userDto;
	
	private List<CommentsDto> comments=new ArrayList<>();
	
	
}
