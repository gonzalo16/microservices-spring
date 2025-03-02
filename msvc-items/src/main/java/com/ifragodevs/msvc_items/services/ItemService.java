package com.ifragodevs.msvc_items.services;

import java.util.List;
import java.util.Optional;

import com.ifragodevs.msvc_items.models.Item;
import com.ifragodevs.msvc_items.models.Product;

public interface ItemService {

	List<Item> findAll();
	
	Optional<Item> findById(Long id);
	
	Product save(Product product);
	
	Product update(Product product, Long id);
	
	void delete(Long id);
}
