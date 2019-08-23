// classe criada para projetarmos somente os dados que queremos do author, no caso, sairá somente Id e nome e não todos os dados dele.
package com.nilton.simplecrud.dto;

import java.io.Serializable;

import com.nilton.simplecrud.domain.User;


public class AuthorDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	
	public AuthorDTO() {
		// TODO Auto-generated constructor stub
	}

	public AuthorDTO(User obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
