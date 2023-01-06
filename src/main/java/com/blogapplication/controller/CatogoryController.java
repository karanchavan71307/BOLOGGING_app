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
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.paylaod.CategoryDto;
import com.blogapplication.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CatogoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	// save category 
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory1(@Valid @RequestBody CategoryDto categoryDto){
		
		CategoryDto dto = this.categoryService.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(dto,HttpStatus.CREATED);
								
	}
	
	// get all category 
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory1(){
		
		List<CategoryDto> list = this.categoryService.getAllCategory();
		
		return new ResponseEntity<List<CategoryDto>>(list,HttpStatus.OK);
	}
	
	// get sigle category 
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getSigleCategory(@PathVariable("id")int id){
		
		CategoryDto categoryDto = this.categoryService.getCatogoryById(id);
		
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	// delete category 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id")int id)
	{
		this.categoryService.deleteCategory(id);
		return ResponseEntity.status(HttpStatus.OK).body("category deleted succesfully !! ");
	}
	
	// update category 
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("id")int id){
		
		CategoryDto dto = this.categoryService.updateCategory(categoryDto, id);
		
		return new ResponseEntity<CategoryDto>(dto,HttpStatus.OK);
	}
	
}
