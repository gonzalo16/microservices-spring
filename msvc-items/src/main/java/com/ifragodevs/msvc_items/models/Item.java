package com.ifragodevs.msvc_items.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {

	private Product product;
	
	private int quantity;
}
