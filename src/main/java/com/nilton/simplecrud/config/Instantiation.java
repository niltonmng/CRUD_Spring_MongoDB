package com.nilton.simplecrud.config;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nilton.simplecrud.domain.User;
import com.nilton.simplecrud.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll(); // limpa a colecao no mongodb
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com"); 
		User alex = new User(null, "Alex Green", "alex@gmail.com"); 
		User bob = new User(null, "Bob Grey", "bob@gmail.com"); 
		
		userRepository.save(maria);
		userRepository.save(alex);
		userRepository.save(bob);
		
	}

}
