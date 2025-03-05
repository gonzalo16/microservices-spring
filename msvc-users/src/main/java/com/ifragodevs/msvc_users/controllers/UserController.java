package com.ifragodevs.msvc_users.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ifragodevs.msvc_users.entities.User;
import com.ifragodevs.msvc_users.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user){
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
	
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		Optional<User> userOptional = userService.findById(id);
		return userOptional.map( user -> ResponseEntity.ok(user)).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User user){
		Optional<User> userOptional = userService.update(user, id);
		
		return userOptional
		        .map(userUpdated -> ResponseEntity.status(HttpStatus.CREATED).body(userUpdated))
		                .orElseGet(() -> ResponseEntity.notFound().build());

	}
}
