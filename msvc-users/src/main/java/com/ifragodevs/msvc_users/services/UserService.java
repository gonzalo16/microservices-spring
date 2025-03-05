package com.ifragodevs.msvc_users.services;

import java.util.Optional;

import com.ifragodevs.msvc_users.entities.User;

public interface UserService {

	User save(User user);
	
	Optional<User> update(User user, Long id);
	
	Optional<User> findById(Long id);
}
