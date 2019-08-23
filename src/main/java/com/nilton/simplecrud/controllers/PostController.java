package com.nilton.simplecrud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nilton.simplecrud.domain.Post;
import com.nilton.simplecrud.domain.services.PostService;


@RestController
@RequestMapping(value="/posts")
public class PostController {
	
	@Autowired
	private PostService service;
	
	@RequestMapping (method=RequestMethod.GET)
	public ResponseEntity<List<Post>> getAll() {
		List<Post> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Post> getById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	
	

}
