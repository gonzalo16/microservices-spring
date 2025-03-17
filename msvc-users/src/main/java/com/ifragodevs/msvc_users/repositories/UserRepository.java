package com.ifragodevs.msvc_users.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ifragodevs.msvc_users.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
