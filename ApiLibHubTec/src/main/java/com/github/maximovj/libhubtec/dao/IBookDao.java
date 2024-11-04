package com.github.maximovj.libhubtec.dao;

import org.springframework.data.repository.CrudRepository;

import com.github.maximovj.libhubtec.model.Book;

public interface IBookDao extends CrudRepository<Book, Long> {

}
