package com.ifragodevs.msvc_products.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ifragodevs.libs_msvc_commons.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
