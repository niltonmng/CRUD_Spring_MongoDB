package com.nilton.simplecrud.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nilton.simplecrud.domain.Post;
import com.nilton.simplecrud.domain.User;
import com.nilton.simplecrud.domain.services.exception.ObjectNotFoundException;
import com.nilton.simplecrud.dto.UserDTO;
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
	
	public User findById(String id) {
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("User not found!")	);
	}
	
	public User insert(User user) {
		return repo.insert(user);
	}
	
	public User fromDtoToUser(UserDTO obj) {
		 return new User(obj.getId(), obj.getName(), obj.getEmail());
	}
	
	public List<UserDTO> transform_listUserDto_to_listUser(List<User> list) {
		List<UserDTO> output = new ArrayList<UserDTO>();
		for (User user : list) {
			UserDTO current = new UserDTO(user);
			output.add(current);
		}
		return output;
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}

	public void delete(String id) {
		findById(id);// fazemos isso para tratar a exception de caso nao exista
		repo.deleteById(id);
	}
	
	public List<Post> getPosts(String id) {
		User u = findById(id);
		return u.getPosts();
	}
	
	
	public List<User> findByName(String name) {
		return repo.findByNameContainingIgnoreCase(name);
	}
	
	public List<User> findByEmail(String email) {
		return repo.findByEmailContainingIgnoreCase(email);
	}

}
