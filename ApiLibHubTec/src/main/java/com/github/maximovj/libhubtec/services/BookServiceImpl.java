package com.github.maximovj.libhubtec.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.maximovj.libhubtec.dao.IBookDao;
import com.github.maximovj.libhubtec.model.Book;

@Service
public class BookServiceImpl implements IBookService {
	
	@Autowired
	private IBookDao dao;

	@Override
	@Transactional(readOnly = true)
	public List<Book> findAllBooks() {
		List<Book> books = new ArrayList<Book>();
		try {
			books = (List<Book>) this.dao.findAll();
		}catch ( Exception e) {
			e.getStackTrace();
		}
		return books;
	}
	
	
	
}
