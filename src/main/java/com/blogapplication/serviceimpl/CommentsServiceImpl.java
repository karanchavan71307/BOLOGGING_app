package com.blogapplication.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapplication.Exceptions.ResourceNotFoundExceptions;
import com.blogapplication.model.Comments;
import com.blogapplication.model.Post;
import com.blogapplication.paylaod.CommentsDto;
import com.blogapplication.repo.CommentsRepo;
import com.blogapplication.repo.PostRepository;
import com.blogapplication.service.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	private CommentsRepo commentsRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PostRepository postRepository;

	// save the comments

	@Override
	public CommentsDto createComments(CommentsDto commentsDto, Integer postId) {

		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundExceptions("post", "post id", postId));

		Comments comments = this.modelMapper.map(commentsDto, Comments.class);

		comments.setPost(post);

		Comments comments2 = this.commentsRepo.save(comments);

		return this.modelMapper.map(comments2, CommentsDto.class);
	}

	// delete comments

	@Override
	public void deleteComments(Integer id) {

		Comments comments = this.commentsRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExceptions("comments", "comments id", id));		
		
		int i = comments.getId();
		
		this.commentsRepo.deleteById(id);

	}

}
