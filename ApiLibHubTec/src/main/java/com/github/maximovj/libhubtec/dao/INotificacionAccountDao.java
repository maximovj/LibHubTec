package com.github.maximovj.libhubtec.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.github.maximovj.libhubtec.model.Account;
import com.github.maximovj.libhubtec.model.NotificationAccount;

public interface INotificacionAccountDao extends CrudRepository<NotificationAccount, Long> {

    // Listar todas las notificaciones de una cuenta en especifico
    public List<NotificationAccount> findAllByAccount(Account account);
    
}
