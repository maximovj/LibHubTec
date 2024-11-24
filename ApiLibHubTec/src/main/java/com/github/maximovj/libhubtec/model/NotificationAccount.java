package com.github.maximovj.libhubtec.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.maximovj.libhubtec.converter.JsonToListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
@Table(name = "notification_accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationAccount {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // No va traer la información de la cuenta
    // solo mostrar `account_id`
    @ManyToOne
    @JoinColumn(name = "account_id" , nullable = false)
    @JsonIgnore
    Account account;

    String subject;
    String content; 
    Boolean send_email;

    @Enumerated(EnumType.STRING)
    NotificationAccountStatus status;
    
    @Convert(converter = JsonToListConverter.class)
    @Column(columnDefinition = "json")
    List<String> tags;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
    
}
