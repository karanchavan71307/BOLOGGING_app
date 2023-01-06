package com.blogapplication.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapplication.Exceptions.ResourceNotFoundExceptions;
import com.blogapplication.model.Category;
import com.blogapplication.paylaod.CategoryDto;
import com.blogapplication.repo.CategoryRepository;
import com.blogapplication.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category category = this.modelMapper.map(categoryDto, Category.class);

		Category category2 = this.categoryRepository.save(category);

		return this.modelMapper.map(category2, CategoryDto.class);

	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int id) {

		Category category = this.categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExceptions("Category ", " categoryId", id));

		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescriptions(categoryDto.getCategoryDescriptions());

		Category category2 = this.categoryRepository.save(category);

		return this.modelMapper.map(category2, CategoryDto.class);

	}

	@Override
	public List<CategoryDto> getAllCategory() {

		List<Category> list = this.categoryRepository.findAll();

		List<CategoryDto> list2 = list.stream().map((list1) -> this.modelMapper.map(list1, CategoryDto.class))
				.collect(Collectors.toList());
		return list2;
	}

	@Override
	public CategoryDto getCatogoryById(int id) {

		Category category = this.categoryRepository.findById(id).orElseThrow(()->new  ResourceNotFoundExceptions("category","categoryId",id));
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int id) {
		
		Category category = this.categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundExceptions("category", "categoryId", id));
		
		this.categoryRepository.delete(category);
		System.out.println("category deleted succesfully !! ");

	}

}
