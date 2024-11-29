package com.github.maximovj.libhubtec.services;

import org.springframework.http.ResponseEntity;
import com.github.maximovj.libhubtec.response.BookResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface IBookServiceImpl {
	
	public ResponseEntity<BookResponse> findAllBooks();

	public ResponseEntity<BookResponse> searchBooks(HttpServletRequest httpRequest);

	public ResponseEntity<BookResponse> findBookById(Long id);

}
