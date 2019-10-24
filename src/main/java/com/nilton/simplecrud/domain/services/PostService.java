package com.nilton.simplecrud.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nilton.simplecrud.domain.Post;
import com.nilton.simplecrud.domain.services.exception.ObjectNotFoundException;
import com.nilton.simplecrud.repository.PostRepository;

@Service
public class PostService {
	
	
	@Autowired
	private PostRepository repo;
	
	@Cacheable(value="post-cache", key="#root.method.name")
	public List<Post> findAll() {
		return repo.findAll();
	}
	
	@CacheEvict(value="post-cache", key= "'PostCache'+#postId", beforeInvocation = true)
	@Cacheable(value="post-cache", key= "'PostCache'+#postId")
	public Post findById(String postId) {
		Optional<Post> post = repo.findById(postId);
		return post.orElseThrow(() -> new ObjectNotFoundException("Post not found!"));
	}
	
	@CacheEvict(cacheNames = "post-cache", key="#id")
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	@CacheEvict(cacheNames = "post-cache", allEntries = true)
	public Post insert(Post post) {
		return repo.insert(post);
	}
	
	public List<Post> findByTitle(String text) {
		return repo.findByTitleContainingIgnoreCase(text);
	}
	
	public List<Post> findByBody(String text){
		return repo.findByBodyContainingIgnoreCase(text);
	}
	
}
