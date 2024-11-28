package com.github.maximovj.libhubtec.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.github.maximovj.libhubtec.model.Announcement;

public interface IAnnouncementDao extends CrudRepository<Announcement, Long> {

    List<Announcement> findAllByOrderByUpdatedAtDesc();
    List<Announcement> findByIsPublishedTrueOrderByUpdatedAtDesc();

}
