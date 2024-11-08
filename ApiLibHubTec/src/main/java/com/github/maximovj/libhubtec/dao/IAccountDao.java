package com.github.maximovj.libhubtec.dao;

import org.springframework.data.repository.CrudRepository;

import com.github.maximovj.libhubtec.model.Account;

public interface IAccountDao extends CrudRepository<Account, Long>  {

}
