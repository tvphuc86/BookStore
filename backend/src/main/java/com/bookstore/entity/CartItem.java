package com.bookstore.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

@Entity
@Table(name = "cart_items")
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int cart_item_quantity;
	
	private Double cart_item_total;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id")
	private Book book;

	public CartItem(Long id, int cart_item_quantity, Double cart_item_total, Book book) {
		super();
		this.id = id;
		this.cart_item_quantity = cart_item_quantity;
		this.cart_item_total = cart_item_total;
		this.book = book;
	}

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCart_item_quantity() {
		return cart_item_quantity;
	}

	public void setCart_item_quantity(int cart_item_quantity) {
		this.cart_item_quantity = cart_item_quantity;
	}

	public Double getCart_item_total() {
		return cart_item_total;
	}

	public void setCart_item_total(Double cart_item_total) {
		this.cart_item_total = cart_item_total;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
}
