package com.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public void AddBook(Book book) {
		
		bookRepository.save(book);
	}
	
	public List<Book> getAllBook(){
		return bookRepository.findAll();
	}
}
