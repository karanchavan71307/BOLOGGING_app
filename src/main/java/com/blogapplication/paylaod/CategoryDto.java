package com.blogapplication.paylaod;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

	private Integer categoryId;
	
	@NotEmpty
	@Size(min = 5,max = 20,message = "title must be between 5-20 chatector !!")
	private String categoryTitle;
	
	@NotEmpty
	@Size(max = 200 )
	private String categoryDescriptions;
}
