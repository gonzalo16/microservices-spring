package com.ifragodevs.msvc_items.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;

import com.ifragodevs.libs_msvc_commons.entities.Product;
import com.ifragodevs.msvc_items.models.Item;
import com.ifragodevs.msvc_items.services.ItemService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class ItemController {

	private final ItemService service;
	
	private final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	private final CircuitBreakerFactory cbFactory;

    public ItemController(ItemService service, CircuitBreakerFactory cbFactory) {
        this.service = service;
        this.cbFactory = cbFactory;
    }

    @GetMapping
    public List<Item> list() {
    	logger.info("Llamada al metodo list del controlador ItemController");
        return service.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> details(@PathVariable Long id) {
    	
        Optional<Item> itemOptional = cbFactory.create("items").run(() -> service.findById(id), e -> {
        	logger.error(e.getMessage());
        	
        	Product product = new Product();
        	product.setCreateAt(LocalDate.now());
        	product.setName("Camara Sony");
        	product.setId(1L);
        	product.setPrice(500L);
        	return Optional.of(new Item(product,5));
        });
        
        
        if(itemOptional.isPresent()){
            return ResponseEntity.ok(itemOptional.get());
        }
        return ResponseEntity.status(404)
                .body(Collections.singletonMap(
                    "message",
                "No existe el producto en el microservicio msvc-products"));
    }
    
    @CircuitBreaker(name = "items",fallbackMethod = "caminoAlternativo")
    @GetMapping("/details/{id}")
    public ResponseEntity<?> details2(@PathVariable Long id) {
    	
        Optional<Item> itemOptional = service.findById(id);
        
        
        if(itemOptional.isPresent()){
            return ResponseEntity.ok(itemOptional.get());
        }
        return ResponseEntity.status(404)
                .body(Collections.singletonMap(
                    "message",
                "No existe el producto en el microservicio msvc-products"));
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
    	logger.info("Creando producto: {}",product);
    	return service.save(product);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@RequestBody Product product, @PathVariable Long id) {
    	return service.update(product,id);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
    	service.delete(id);
    }
    
    
    //CAMINO ALTERNATIVO
    public ResponseEntity<?> caminoAlternativo(Throwable e){
    	logger.error(e.getMessage());
    	
    	Product product = new Product();
    	product.setCreateAt(LocalDate.now());
    	product.setName("Camara Sony");
    	product.setId(1L);
    	product.setPrice(500L);
    	return ResponseEntity.ok(new Item(product,5));
    }
}
