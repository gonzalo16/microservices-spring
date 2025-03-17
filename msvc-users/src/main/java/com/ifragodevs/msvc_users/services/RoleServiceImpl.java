package com.ifragodevs.msvc_users.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifragodevs.msvc_users.entities.Role;
import com.ifragodevs.msvc_users.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Optional<Role> findById(Long id) {
		return roleRepository.findById(id);
	}

	@Override
	public Optional<Role> findByName(String roleName) {
		return roleRepository.findByName(roleName);
	}

	
}
