package com.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "id")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String book_name;

	private Double book_price;

	private int book_quantity;

	private String book_lang;

	private String book_description;
	
	@Lob
	@Column(length = 100000)
	private byte[] book_image;
	
	@ManyToOne()
	@JoinColumn(name = "author_id", referencedColumnName = "id")
	private Author author;

	@ManyToOne()
	@JoinColumn(name = "category_id",referencedColumnName = "id")
	private Category category;

	@ManyToOne()
	@JoinColumn(name = "publisher_id",referencedColumnName = "id")
	private Publisher publisher;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public Double getBook_price() {
		return book_price;
	}

	public void setBook_price(Double book_price) {
		this.book_price = book_price;
	}

	public int getBook_quantity() {
		return book_quantity;
	}

	public void setBook_quantity(int book_quantity) {
		this.book_quantity = book_quantity;
	}

	public String getBook_lang() {
		return book_lang;
	}

	public void setBook_lang(String book_lang) {
		this.book_lang = book_lang;
	}

	public String getBook_description() {
		return book_description;
	}

	public void setBook_description(String book_description) {
		this.book_description = book_description;
	}

	

	public byte[] getBook_image() {
		return book_image;
	}

	public void setBook_image(byte[] book_image) {
		this.book_image = book_image;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}





	
}
