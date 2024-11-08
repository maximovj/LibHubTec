package com.github.maximovj.libhubtec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.github.maximovj.libhubtec.model.Book;
import com.github.maximovj.libhubtec.response.BookResponse;
import com.github.maximovj.libhubtec.services.IBookServiceImpl;

@RestController
@RequestMapping("/v1")
public class BookRestController {
	
	@Autowired
	private IBookServiceImpl bookService;

	@GetMapping("/books")
	public ResponseEntity<BookResponse> findAllBooks(){
		return this.bookService.findAllBooks();
	}

}
