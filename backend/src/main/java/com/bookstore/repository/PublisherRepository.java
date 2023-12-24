package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}
