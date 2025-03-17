package com.ifragodevs.msvc_users.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	private Boolean enabled;
	
	@Column(unique = true)
	private String email;
	
	@Builder.Default
	@ManyToMany
	@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
	/*
	 * Tabla de union. Debido a que la relacion es de muchos a muchos entre roles y users.
		y contiene la clave foranea de ambas tablas.
		Tenemos las dos claves foraneas user_id y role_id
		y el conjunto de las dos claves tiene que ser unico
		y eso lo indicamos con @UniqueConstraint
	 * */
	@JoinTable(name = "users_roles",
				joinColumns = {@JoinColumn(name ="user_id")},
				inverseJoinColumns = {@JoinColumn(name = "role_id")},
				uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","role_id"})})
	private List<Role> roles = new ArrayList<>();
}
