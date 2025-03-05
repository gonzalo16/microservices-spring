package com.ifragodevs.msvc_users.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ifragodevs.msvc_users.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

	@Query("SELECT r FROM Role r WHERE r.name = :name")
	Optional<Role> findByName(String name);
}
