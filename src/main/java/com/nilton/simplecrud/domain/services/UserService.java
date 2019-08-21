package com.nilton.simplecrud.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nilton.simplecrud.domain.User;
import com.nilton.simplecrud.repository.UserRepository;

@Service
public class UserService {
	
	// para instanciar automaticamente um objeto aqui no serviço usamos a anotacao abaixo
	// quando declaramos o objeto com a anotação autowired, o proprio spring vai procurar a definicao desse objeto
	// que no caso é o UserRepository e automaticamente instancia o objeto pra nós.
	// isso é o mecanismo de injeção de dependencia automatica do spring.
	
	@Autowired
	private UserRepository repo;
	
	
	public List<User> findAll() {
		return repo.findAll();
	}

}
