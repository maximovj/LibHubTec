package com.github.maximovj.libhubtec.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.maximovj.libhubtec.converter.JsonToListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "announcements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //@Column(name = "moonshine_user_id", nullable = true)
    //@JsonIgnore
    //Long moonshineUserId;

    String title;
    String content;
    String link;
    String tags;

    @Column(name = "is_published")
    Boolean isPublished;

    @Convert(converter = JsonToListConverter.class)
    @Column(columnDefinition = "json")
    List<String> pictures;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime updatedAt;

}
