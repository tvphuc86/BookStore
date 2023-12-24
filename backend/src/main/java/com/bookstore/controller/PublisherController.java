package com.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entity.Publisher;
import com.bookstore.repository.PublisherRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/publishers")
public class PublisherController {
	
	private PublisherRepository publisherRepository;
	
	public PublisherController(PublisherRepository publisherRepos) {
		this.publisherRepository = publisherRepos;
	}
	
	@GetMapping
	public List<Publisher> getAllCategories() {
		return publisherRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Publisher> getBookById(@PathVariable("id") Long id) {
		return publisherRepository.findById(id);
	}
}
