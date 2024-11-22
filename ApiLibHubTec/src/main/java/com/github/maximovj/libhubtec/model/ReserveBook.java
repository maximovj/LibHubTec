package com.github.maximovj.libhubtec.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reserve_books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = true)
    Account account;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = true)
    Book book;

    String account_username;
    String account_email;
    String account_name;
    String account_last_name;
    String book_title;
    String book_author;
    Integer book_count;
    BigDecimal book_price;
    LocalDateTime date_from;
    LocalDateTime date_to;
    Boolean active;
    
    @Enumerated(EnumType.STRING)
    ReserveBookStatus status;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    LocalDateTime updateAt;

}
