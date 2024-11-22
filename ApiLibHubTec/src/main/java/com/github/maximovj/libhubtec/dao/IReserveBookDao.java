package com.github.maximovj.libhubtec.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.github.maximovj.libhubtec.model.Account;
import com.github.maximovj.libhubtec.model.Book;
import com.github.maximovj.libhubtec.model.ReserveBook;

public interface IReserveBookDao extends CrudRepository<ReserveBook, Long> 
{

    Optional<ReserveBook> findByAccountAndBook(Account account, Book book);
    
    List<ReserveBook> findByAccount(Account account);
    
}
