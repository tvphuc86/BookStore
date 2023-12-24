package com.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authors")
public class AuthorController {
	private final AuthorRepository authorRepository;

	public AuthorController(AuthorRepository authorRepos) {
		this.authorRepository = authorRepos;
	}

	@GetMapping
	public List<Author> getAllAuthor() {
		return authorRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Author> getBookById(@PathVariable("id") Long id) {
		return authorRepository.findById(id);
	}
}
