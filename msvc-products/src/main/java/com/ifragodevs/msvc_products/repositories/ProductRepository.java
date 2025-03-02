package com.ifragodevs.msvc_products.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ifragodevs.msvc_products.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
