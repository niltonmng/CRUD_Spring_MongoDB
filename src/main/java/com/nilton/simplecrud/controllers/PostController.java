package com.nilton.simplecrud.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nilton.simplecrud.domain.Post;
import com.nilton.simplecrud.domain.services.PostService;
import com.nilton.simplecrud.dto.CommentDTO;

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
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Post> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}/comments", method=RequestMethod.GET)
	public ResponseEntity<List<CommentDTO>> getComment(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getComment());
	}
	
	@RequestMapping(method=RequestMethod.POST)
 	public ResponseEntity<Void> insert(@RequestBody Post obj) { 
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); 
		return ResponseEntity.created(uri).build();
	}
	
}