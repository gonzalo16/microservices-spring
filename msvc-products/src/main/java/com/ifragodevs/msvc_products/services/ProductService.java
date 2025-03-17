package com.ifragodevs.msvc_products.services;

import java.util.List;
import java.util.Optional;

import com.ifragodevs.libs_msvc_commons.entities.Product;

public interface ProductService {

	List<Product> findAll();
	
	Optional<Product> findById(Long id);
	
	Product save(Product product);
	
	void deleteById(Long id);
}
