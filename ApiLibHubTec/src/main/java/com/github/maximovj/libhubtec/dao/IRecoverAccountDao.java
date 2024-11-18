package com.github.maximovj.libhubtec.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.github.maximovj.libhubtec.model.RecoverAccount;

public interface IRecoverAccountDao extends CrudRepository<RecoverAccount, Long> {

    public Optional<RecoverAccount> findByAccountId(Long id);
    public Optional<RecoverAccount> findByEmail(String email);
    
}
