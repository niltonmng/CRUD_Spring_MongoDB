package com.nilton.simplecrud.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nilton.simplecrud.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	// criamos esta interface para ter acesso à api, permitindo poder criar o crud de operações necessarias a cada modelo, neste caso o User.
	
	
	public List<User> findByNameContainingIgnoreCase(String text);
	
	public List<User> findByEmailContainingIgnoreCase(String text);
	
}
