package com.bookstore.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bookstore.dto.BookDTO;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private BookRepository bookRepos;
	
	@Autowired
	private AuthorRepository authorRepos;
	
//	private BookService bookService;
//	
//	public AdminController(BookService bookSV) {
//		this.bookService = bookSV;
//	}
	
	@GetMapping("/book/list-books")
	public String listBook (Model model) {
		List<Book> listBooks = bookRepos.findAll();
		//List<Book> listBooks = bookService.getAllBook();
		List<BookDTO> bookDTOs = new ArrayList<>();
		for(Book book : listBooks) {
			BookDTO bookdto = new BookDTO();
			bookdto.setId(book.getId());
			bookdto.setBook_description(book.getBook_description());
			bookdto.setBook_lang(book.getBook_lang());
			bookdto.setBook_name(book.getBook_name());
			bookdto.setBook_price(book.getBook_price());
			bookdto.setBook_quantity(book.getBook_quantity());
			String base64String = Base64.getEncoder().encodeToString(book.getBook_image());
			bookdto.setBook_image(base64String);
			bookDTOs.add(bookdto);
		}
		model.addAttribute("listbooks", bookDTOs);
		return "/admin/book/listbook";
	}
	
	public BookDTO convertBook2BookDTO(Book book) {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setBook_description(book.getBook_description());
		String base64String = Base64.getEncoder().encodeToString(book.getBook_image());
		bookDTO.setBook_image(base64String);
		bookDTO.setBook_lang(book.getBook_lang());
		bookDTO.setBook_name(book.getBook_name());
		bookDTO.setBook_price(book.getBook_price());
		bookDTO.setBook_quantity(book.getBook_quantity());
		bookDTO.setId(book.getId());
		return bookDTO;
	}
	
	@GetMapping("/book/editbook/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		Book book = bookRepos.findById(id).orElseThrow( () -> new IllegalArgumentException("Book not Found!"));
		BookDTO bookDTO = convertBook2BookDTO(book);
		model.addAttribute("editbook",bookDTO);
		return "admin/book/editbook";
	}
	
	@PostMapping("/book/editbook/{id}")
	public String saveEditBook(@PathVariable("id") Long id, @RequestParam("book_name") String bookname,
			@RequestParam("book_description") String bookdes, @RequestParam("book_price") Double bookprice, 
			@RequestParam("book_quantity") int bookquantity , Model model)  {
		Book editBook = bookRepos.findById(id).orElseThrow(() -> new IllegalArgumentException("Book Not Found!"));
		editBook.setBook_name(bookname);
		editBook.setBook_description(bookdes);
		editBook.setBook_price(bookprice);
		editBook.setBook_quantity(bookquantity);
		bookRepos.save(editBook);
		BookDTO editBookDTO = convertBook2BookDTO(editBook);
		model.addAttribute("editbook",editBookDTO);
		return "admin/book/editbook";
	}
	
	@GetMapping("/book/addbook")
	public String addBookForm() {
		return "/admin/book/addbook";
	}
	
	// Quản lý author
	@GetMapping("/author/list-authors")
	public String getAllAuthors(Model model) {
		List<Author> listauthors = authorRepos.findAll();
		model.addAttribute("authors",listauthors);
		return "/admin/author/listauthor";
	}
	
}
