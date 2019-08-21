package com.nilton.simplecrud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nilton.simplecrud.domain.User;
import com.nilton.simplecrud.domain.services.UserService;

@RestController // define que é um controller da api rest a ser construida
@RequestMapping(value="/users") // caminho do endpoint
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET) // definindo um método get para o caminho acima.
	public ResponseEntity<List<User>> findAll(){
		List<User> output = service.findAll();
		return ResponseEntity.ok().body(output);
	}
	

}
