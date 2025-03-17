package com.ifragodevs.msvc_items.models;

import com.ifragodevs.libs_msvc_commons.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {

	private Product product;
	
	private int quantity;
}
