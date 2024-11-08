package com.github.maximovj.libhubtec.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.github.maximovj.libhubtec.response.BookResponse;

public interface IBookService {
	
	public ResponseEntity<BookResponse> findAllBooks();

}
