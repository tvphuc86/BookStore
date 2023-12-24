package com.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
	
    private final BookRepository bookRepository;
    
	public BookController(BookRepository bookRepos) {
		this.bookRepository = bookRepos;
	}

	@GetMapping
	public  List<Book> getAllBook(){
		List<Book> books = bookRepository.findAll();
		return books;
	}
	
	@GetMapping("/{id}")
	public Optional<Book> getBookById(@PathVariable("id") Long id) {
		return bookRepository.findById(id);
	}
	
	
}
