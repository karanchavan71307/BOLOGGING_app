package com.blogapplication.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;

import com.blogapplication.Exceptions.ResourceNotFoundExceptions;
import com.blogapplication.model.Category;
import com.blogapplication.model.Post;
import com.blogapplication.model.User;
import com.blogapplication.paylaod.CategoryDto;
import com.blogapplication.paylaod.PostDto;
import com.blogapplication.paylaod.PostResponse;
import com.blogapplication.paylaod.UserDto;
import com.blogapplication.repo.CategoryRepository;
import com.blogapplication.repo.PostRepository;
import com.blogapplication.repo.UserRepository;
import com.blogapplication.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	
	
	private static final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

	
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoryRepository categoryRepository;;

	@Autowired
	private UserRepository userRepository;

	// create post 
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		// fatch user and category

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundExceptions("user", "user id", userId));
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundExceptions("category", "categoryId", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);

		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newPost = this.postRepository.save(post);

		return this.modelMapper.map(newPost, PostDto.class);
	}
// update post 
	@Override
	public PostDto updatePost(PostDto postDto, int postId) {

		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundExceptions("post", "postId", postId));

		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
        
		Post post2 = this.postRepository.save(post);

		return this.modelMapper.map(post2, PostDto.class);
	}

	// delete post

	@Override
	public void deletePost(Integer postId) {

		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundExceptions("post", "postId", postId));

		this.postRepository.delete(post);
		System.out.println("deleted !!");

	}

	// get all post
	@Override
	public PostResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir) {

		Sort sort = null;

		if (sortDir.equalsIgnoreCase("asc")) {

			sort = Sort.by(sortBy);

		} else {
			sort = Sort.by(sortBy).descending();
		}

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagepost = this.postRepository.findAll(p);

		List<Post> list = pagepost.getContent();

		List<PostDto> list2 = list.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();

		postResponse.setContent(list2);
		postResponse.setPageNumber(pagepost.getNumber());
		postResponse.setPageSize(pagepost.getSize());
		postResponse.setTotalElements(pagepost.getTotalElements());
		postResponse.setTotalPage(pagepost.getTotalPages());

		postResponse.setLastPage(pagepost.isLast());

		return postResponse;
	}
  // get single post 
	@Override
	public PostDto getSignlePost(int postId) {

		log.info("fetching post from data base !!");
		
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundExceptions("post", "postid", postId));
		
		log.info("fetching post succesfully !!");
		
		
		
		
		return this.modelMapper.map(post, PostDto.class);
	}

	// find by category
	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {

		Category category2 = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundExceptions("category", "categoryid", categoryId));

		List<Post> list = this.postRepository.findByCategory(category2);

		List<PostDto> list2 = list.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return list2;
	}

	// find by user
	@Override
	public List<PostDto> getPostByUser(Integer userId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundExceptions("user", "userid", userId));

		List<Post> list = this.postRepository.findByUser(user);

		List<PostDto> list2 = list.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return list2;
	}

	@Override
	public List<PostDto> SerarchPost(String keyword) {

		List<PostDto> list = this.postRepository.findByTitleContaining(keyword);

		return list;
	}

}
