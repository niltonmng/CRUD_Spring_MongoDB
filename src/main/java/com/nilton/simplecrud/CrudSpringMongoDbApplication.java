package com.nilton.simplecrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CrudSpringMongoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringMongoDbApplication.class, args);
	}

}
