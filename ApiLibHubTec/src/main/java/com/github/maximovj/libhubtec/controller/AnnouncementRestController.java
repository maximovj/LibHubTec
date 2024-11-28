package com.github.maximovj.libhubtec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.libhubtec.response.AnnouncementResponse;
import com.github.maximovj.libhubtec.services.IAnnouncementServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/v1")
public class AnnouncementRestController implements IAnnouncementServiceImpl {

    @Autowired
    private IAnnouncementServiceImpl announcementServiceImpl;

    @GetMapping("/announcements")
    @Override
    public ResponseEntity<AnnouncementResponse> listAnnouncements() {
        return this.announcementServiceImpl.listAnnouncements();
    }
    
}
