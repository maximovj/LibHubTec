package com.github.maximovj.libhubtec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.libhubtec.response.BookResponse;
import com.github.maximovj.libhubtec.services.IBookServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/v1")
public class BookRestController {
	
	@Autowired
	private IBookServiceImpl bookService;

	@GetMapping("/books")
	public ResponseEntity<BookResponse> findAllBooks()
	{
		return this.bookService.findAllBooks();
	}

	@GetMapping("/books/search")
	public ResponseEntity<BookResponse> searchBooks(HttpServletRequest httpRequest)
	{
		return this.bookService.searchBooks(httpRequest);
	}

	@GetMapping("/books/{id}/book-details")
	public ResponseEntity<BookResponse> bookDetails(@PathVariable Long id)
	{
		return this.bookService.findBookById(id);
	}	

}
