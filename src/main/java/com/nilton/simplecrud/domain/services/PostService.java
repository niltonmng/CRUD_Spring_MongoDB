package com.nilton.simplecrud.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nilton.simplecrud.domain.Post;
import com.nilton.simplecrud.domain.services.exception.ObjectNotFoundException;
import com.nilton.simplecrud.repository.PostRepository;

@Service
public class PostService {
	
	
	@Autowired
	private PostRepository repo;
	
	
	public List<Post> findAll() {
		return repo.findAll();
	}
	
	public Post findById(String id) {
		Optional<Post> post = repo.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Post not found!"));
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public Post insert(Post post) {
		return repo.insert(post);
	}
	
	public List<Post> findByTitle(String text) {
		return repo.findByTitleContaining(text);
	}
	
}
