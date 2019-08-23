package com.nilton.simplecrud.config;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nilton.simplecrud.domain.Post;
import com.nilton.simplecrud.domain.User;
import com.nilton.simplecrud.dto.AuthorDTO;
import com.nilton.simplecrud.repository.PostRepository;
import com.nilton.simplecrud.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner { // permite que a nossa base de dados inicie com as specs definidas por nós

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT")); // consideramos que está no horário de Londres.
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com"); 
		User alex = new User(null, "Alex Green", "alex@gmail.com"); 
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.save(maria);
		userRepository.save(alex);
		userRepository.save(bob);
		
		Post post1 = new Post(null, sdf.parse("23/03/2019"), "Control to Bridge! This is Sally.", "How are you on this lovely morning?", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2100"), "Good Morning Sally!", "Another day in Paradise!", new AuthorDTO(maria));
		
		postRepository.save(post1);
		postRepository.save(post2);
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
	}

}
