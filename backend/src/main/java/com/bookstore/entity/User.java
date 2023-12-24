package com.bookstore.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String avatar;
	
	private String phone;
	
	private String address;
	
	private String role;
	
	@OneToMany(cascade = CascadeType.ALL, 
			mappedBy = "user", orphanRemoval = true)
	private List<Order> orders = new ArrayList<>();
}
