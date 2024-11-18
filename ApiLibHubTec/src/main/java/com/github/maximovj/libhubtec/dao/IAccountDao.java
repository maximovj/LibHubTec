package com.github.maximovj.libhubtec.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.github.maximovj.libhubtec.model.Account;

public interface IAccountDao extends CrudRepository<Account, Long>  {

    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.password = :password WHERE id = :id")
    void updatePasswordById(Long id, String password);

}
