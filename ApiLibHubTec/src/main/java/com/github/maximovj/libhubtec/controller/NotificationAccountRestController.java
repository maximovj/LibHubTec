package com.github.maximovj.libhubtec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.libhubtec.response.NotificationAccountResponse;
import com.github.maximovj.libhubtec.services.INotificationAccountServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/v1")
public class NotificationAccountRestController implements INotificationAccountServiceImpl  {

    @Autowired
    private INotificationAccountServiceImpl accountServiceImpl;

    @GetMapping("/notification/accounts/{account_id}/account/list")
    @Override
    public ResponseEntity<NotificationAccountResponse> listNotifications(@PathVariable Long account_id) {
        return this.accountServiceImpl.listNotifications(account_id);
    }

    @PutMapping("/notification/accounts/{notificacion_account_id}/notification/read")
    @Override
    public ResponseEntity<NotificationAccountResponse> markReadNotification(@PathVariable Long notificacion_account_id) {
        return this.accountServiceImpl.markReadNotification(notificacion_account_id);
    }

}
