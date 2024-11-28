package com.github.maximovj.libhubtec.services;

import org.springframework.http.ResponseEntity;

import com.github.maximovj.libhubtec.response.AnnouncementResponse;

public interface IAnnouncementServiceImpl {
    
    // Proporcionar una lista los anuncios
    public ResponseEntity<AnnouncementResponse> listAnnouncements();

}
