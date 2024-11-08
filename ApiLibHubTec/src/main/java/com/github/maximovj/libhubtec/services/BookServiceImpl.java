package com.github.maximovj.libhubtec.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.function.EntityResponse;

import com.github.maximovj.libhubtec.dao.IBookDao;
import com.github.maximovj.libhubtec.model.Book;
import com.github.maximovj.libhubtec.response.ApiResponse;
import com.github.maximovj.libhubtec.response.BookResponse;

import jakarta.persistence.Entity;

@Service
public class BookServiceImpl implements IBookServiceImpl {
	
	private BookResponse response;
	
	@Autowired
	private IBookDao dao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<BookResponse> findAllBooks() {
		List<Book> books = new ArrayList<Book>();
		
		this.response = new BookResponse();
		
		try {
			this.response.setResponse(new ApiResponse(
					"Listar libros", 
					"Cuentas listados correctamente", 
					"/v1/books", 
					"GET", 
					HttpStatus.OK.value(), 
					"success", 
					true));
		
			books = (List<Book>) this.dao.findAll();
		}catch ( Exception e) {
			this.response.setResponse(new ApiResponse(
					"Listar libros", 
					"Error al obtener los libros", 
					"/v1/books", 
					"GET", 
					HttpStatus.NO_CONTENT.value(), 
					"error", 
					false));
			
			e.getStackTrace();
			System.out.println("BookServiceImpl::findAllBooks | ** Error **");
			System.out.println(e.getMessage());
		}
		
		this.response.setData(Optional.ofNullable(books));
		return ResponseEntity.ok(this.response);
	}
	
}
