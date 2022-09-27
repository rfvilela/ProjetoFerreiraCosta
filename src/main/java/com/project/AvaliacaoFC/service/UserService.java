package com.project.AvaliacaoFC.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.AvaliacaoFC.entity.UserFC;
import com.project.AvaliacaoFC.repository.UserRepository;

@Service
public class UserService {
	
	
	
	private final UserRepository userRepository;
	
	
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	
	// CRUD READ all
	public List<UserFC>findAll(String name) {
		 if (name == null) {
	            return userRepository.findAll();
	        } else {
	            return userRepository.findByNameContainingIgnoreCase(name);
	        }
	    }
	
	// CRUD READ by ID
			public UserFC findById(Long id) {
				Optional<UserFC> optional = userRepository.findById(id);
				UserFC users = optional.orElse(null);
				return users;
			}
			
			public UserFC create(UserFC user) {

				user.setId(null);
				user = userRepository.save(user);
				return user;
				
			}
			
			// CRUD: Update
			public UserFC update(UserFC user) {

				Long id = user.getId();
				if (id != null && userRepository.existsById(id)) {
					user = userRepository.save(user);
					return user;
				} else {
					return null;
				}
			}
			public void deleteById(Long id) {
				if (id != null && userRepository.existsById(id)) {
					userRepository.deleteById(id);
				}
			}

			public void deleteAll() {

				userRepository.deleteAllInBatch();
			}
			
			

			
			
			
			

}