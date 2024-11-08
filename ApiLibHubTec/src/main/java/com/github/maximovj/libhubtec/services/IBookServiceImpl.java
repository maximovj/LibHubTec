package com.github.maximovj.libhubtec.services;

import org.springframework.http.ResponseEntity;
import com.github.maximovj.libhubtec.response.BookResponse;

public interface IBookServiceImpl {
	
	public ResponseEntity<BookResponse> findAllBooks();

}