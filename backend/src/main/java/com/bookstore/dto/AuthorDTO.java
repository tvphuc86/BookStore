package com.bookstore.dto;

import java.util.List;

import com.bookstore.entity.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

public class AuthorDTO {
	private String author_name;
	private String author_description;
	private List<Book> books;

}
