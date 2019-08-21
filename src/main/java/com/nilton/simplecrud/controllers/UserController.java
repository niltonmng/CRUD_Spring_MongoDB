package com.nilton.simplecrud.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nilton.simplecrud.domain.User;

@RestController // define que é um controller da api rest a ser construida
@RequestMapping(value="/users") // caminho do endpoint
public class UserController {
	
	@RequestMapping(method = RequestMethod.GET) // definindo um método get para o caminho acima.
	public ResponseEntity<List<User>> findAll(){
		User k = new User("1", "Ketsia Ginani", "ketsiaginani@gmail.com");
		User g = new User("2", "Ginani", "ginani@gmail.com");
		List<User> output = new ArrayList<User>();
		output.addAll(Arrays.asList(k,g));
		return ResponseEntity.ok().body(output);
	}
	

}
