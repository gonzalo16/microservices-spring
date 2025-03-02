package com.ifragodevs.msvc_items.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

	private Long id;
	
	private String name;
	
	private Double price;
	
	private LocalDate createAt;
}
