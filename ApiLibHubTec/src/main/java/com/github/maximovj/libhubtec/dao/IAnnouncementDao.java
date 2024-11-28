package com.github.maximovj.libhubtec.dao;

import org.springframework.data.repository.CrudRepository;

import com.github.maximovj.libhubtec.model.Announcement;

public interface IAnnouncementDao extends CrudRepository<Announcement, Long> {

}
