package com.nilton.simplecrud.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nilton.simplecrud.domain.User;
import com.nilton.simplecrud.domain.services.UserService;
import com.nilton.simplecrud.dto.UserDTO;

@RestController // define que é um controller da api rest a ser construida
@RequestMapping(value="/users") // caminho do endpoint
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET) // definindo um método get para o caminho acima.
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = service.findAll(); // isto permanece, pois temos que carregar normalmente a lista de users
//		List<UserDTO> listDto = list.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		List<UserDTO> listDto = this.transform(list);
		return ResponseEntity.ok().body(listDto);
	}
	
	private List<UserDTO> transform(List<User> list) {
		List<UserDTO> output = new ArrayList<UserDTO>();
		for (User user : list) {
			UserDTO current = new UserDTO(user);
			output.add(current);
		}
		return output;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
 	public ResponseEntity<UserDTO> findById(@PathVariable String id) { // Pathvariable indica que o id passado como parametro casa com o da url
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}

}
