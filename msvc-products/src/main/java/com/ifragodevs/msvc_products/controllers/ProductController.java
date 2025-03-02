package com.ifragodevs.msvc_products.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ifragodevs.msvc_products.entities.Product;
import com.ifragodevs.msvc_products.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<?> list(){
		return ResponseEntity.ok(this.productService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> details(@PathVariable Long id) {
		Optional<Product> productOptional = productService.findById(id);
		if(productOptional.isPresent()) {
			return ResponseEntity.ok(productOptional.get());
		}	
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product){
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product productNew){
		Optional<Product> productOptional = productService.findById(id);
		if(productOptional.isPresent()) {
			Product productDb = productOptional.orElseThrow();
			
			productDb.setName(productNew.getName());
			productDb.setPrice(productNew.getPrice());
			productDb.setCreateAt(productDb.getCreateAt());
						
			return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productDb));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		Optional<Product> productOptional = productService.findById(id);
		if(productOptional.isPresent()) {
			productService.deleteById(id);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
				
	}
}
