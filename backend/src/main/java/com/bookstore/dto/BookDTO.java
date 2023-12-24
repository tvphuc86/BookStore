package com.bookstore.dto;



public class BookDTO {
	
	private Long id;

	private String book_name;

	private Double book_price;

	private int book_quantity;

	private String book_lang;

	private String book_description;
	
	private String book_image;
	
	

	public BookDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookDTO(Long id, String book_name, Double book_price, int book_quantity, String book_lang,
			String book_description, String book_image) {
		super();
		this.id = id;
		this.book_name = book_name;
		this.book_price = book_price;
		this.book_quantity = book_quantity;
		this.book_lang = book_lang;
		this.book_description = book_description;
		this.book_image = book_image;
	}

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

	public String getBook_image() {
		return book_image;
	}

	public void setBook_image(String book_image) {
		this.book_image = book_image;
	}
	
	
}
