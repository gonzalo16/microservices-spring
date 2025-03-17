package com.ifragodevs.msvc_users.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifragodevs.msvc_users.entities.Role;
import com.ifragodevs.msvc_users.entities.User;
import com.ifragodevs.msvc_users.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;

	@Override
	@Transactional
	public User save(User user) {
		List<Role> roleList = new ArrayList<>();
		Optional<Role> roleName = roleService.findByName("ROLE_USER");
		
		if(roleName.isPresent()) {
			roleList.add(roleName.get());
			user.setRoles(roleList);
		}

		return userRepository.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	@Transactional
	public Optional<User> update(User user, Long id) {
		List<Role> roleList = new ArrayList<>();
		Optional<Role> roleName = roleService.findByName("ROLE_USER");
		Optional<User> userDb = userRepository.findById(id);
		
		return userDb.map(u -> {
			u.setEmail(user.getEmail());
			u.setUsername(user.getUsername());
			if(user.getEnabled() != null ) {
				u.setEnabled(user.getEnabled());
			}
			
			if(roleName.isPresent()) {
				roleList.add(roleName.get());
				user.setRoles(roleList);
			}
			
			return Optional.of(userRepository.save(u));
		}).orElseGet(() -> Optional.empty());
	}
}
