package com.github.maximovj.libhubtec.services;

import org.springframework.http.ResponseEntity;

import com.github.maximovj.libhubtec.response.NotificationAccountResponse;

public interface INotificationAccountServiceImpl 
{
 
    // Listar las notificaciones de una cuenta
    public ResponseEntity<NotificationAccountResponse> listNotifications(Long account_id);

    // Marcar con leído una notificación
    public ResponseEntity<NotificationAccountResponse> markReadNotification(Long notificacion_account_id);

}
