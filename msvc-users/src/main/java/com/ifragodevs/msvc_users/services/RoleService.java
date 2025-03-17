package com.ifragodevs.msvc_users.services;

import java.util.Optional;

import com.ifragodevs.msvc_users.entities.Role;

public interface RoleService {

	Optional<Role> findById(Long id);
	
	Optional<Role> findByName(String roleName);
	
}
