package com.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.bookstore.entity.Category;
import com.bookstore.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
	private final CategoryRepository categoryRepository;

	public CategoryController(CategoryRepository categoryRepos) {
		this.categoryRepository = categoryRepos;
	}
	
	@GetMapping
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Category> getBookById(@PathVariable("id") Long id) {
		return categoryRepository.findById(id);
	}
}
