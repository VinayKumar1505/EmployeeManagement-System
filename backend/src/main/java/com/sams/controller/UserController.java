package com.sams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sams.exception.UserNotFoundException;
import com.sams.model.User;
import com.sams.repository.UserRepository;

@RestController
@CrossOrigin(origins="*")
public class UserController {

	@Autowired
	UserRepository userRepo;

	@PostMapping("/user")
	User addUser(@RequestBody User newUser) {
		return userRepo.save(newUser);
	}
	
	@GetMapping("/users")
	List<User> getUsers(){
		return userRepo.findAll();
	}
	
	@GetMapping("/user/{id}")
	User getUserByid(@PathVariable Long id) {
		return userRepo.findById(id)
				.orElseThrow(()->new UserNotFoundException(id));
	}
	
	@PutMapping("/user/{id}")
	User updateUser(@RequestBody User newUser, @PathVariable Long id) {
		return userRepo.findById(id)
				.map(user->{
					user.setName(newUser.getName());
					user.setUsername(newUser.getUsername());
					user.setEmail(newUser.getEmail());
					return userRepo.save(user);
				})
				.orElseThrow(()-> new UserNotFoundException(id));
	}
	
	@DeleteMapping("/user/{id}")
	String deleteUser(@PathVariable Long id) {
		if(!userRepo.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userRepo.deleteById(id);
		
		return "user with id "+id+ " has been delted";
	}
	
	
}
