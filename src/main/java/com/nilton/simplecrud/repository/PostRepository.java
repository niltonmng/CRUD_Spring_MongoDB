package com.nilton.simplecrud.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nilton.simplecrud.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	// implementacao de query methods
	// disponível em (https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#repositories.query-methods)
	//public List<Post> findByTitleContainingIgnoreCase(String text);
	
	//{ <field>: { $regex: /pattern/, $options: '<options>' } } expressao regular padrão, forma mais declarativa de fazer
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	public List<Post> searchTitle(String text);

}
