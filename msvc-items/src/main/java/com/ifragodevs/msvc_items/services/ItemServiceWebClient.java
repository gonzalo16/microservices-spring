package com.ifragodevs.msvc_items.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ifragodevs.libs_msvc_commons.entities.Product;
import com.ifragodevs.msvc_items.models.Item;

@Primary
@Service
public class ItemServiceWebClient implements ItemService{

	private final WebClient client;
	
	public ItemServiceWebClient(WebClient client) {
		this.client=client;
	}
	
	@Override
	public List<Item> findAll() {
		return this.client
				.get()
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(Product.class)
				.map(product -> new Item(product,new Random().nextInt(10)+1))
				.collectList()
				.block();
	}

	@Override
	public Optional<Item> findById(Long id) {
		Map<String,Object> params = new HashMap<>();
		params.put("id", id);
		
		return Optional.ofNullable(
				this.client
				.get()
				.uri("/{id}",params)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Product.class)
				.map(p -> new Item(p,new Random().nextInt(10)+1))
				.block());
	}

	@Override
	public Product save(Product product) {
		return client
				.post()
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(product)
				.retrieve()
				.bodyToMono(Product.class)
				.block();
	}

	@Override
	public Product update(Product product, Long id) {
		Map<String,Object> params = new HashMap<>();
		params.put("id", id);
		
		return this.client
		.put()
		.uri("/{id}",params)
		.accept(MediaType.APPLICATION_JSON)
		.contentType(MediaType.APPLICATION_JSON)
		.bodyValue(product)
		.retrieve()
		.bodyToMono(Product.class)
		.block();
		
	}

	@Override
	public void delete(Long id) {
		Map<String,Object> params = new HashMap<>();
		params.put("id", id);
		
		this.client
			.delete()
			.uri("/{id}",params)
			.retrieve()
			.bodyToMono(Void.class)
			.block();
	}

}
