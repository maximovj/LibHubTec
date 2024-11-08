package com.github.maximovj.libhubtec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.libhubtec.model.Book;
import com.github.maximovj.libhubtec.services.IBookService;

@RestController
@RequestMapping("/v1")
public class BookRestController {
	
	@Autowired
	private IBookService bookService;

	@GetMapping("/books")
	public List<Book> findAllBooks(){
		return this.bookService.findAllBooks();
	}

}
