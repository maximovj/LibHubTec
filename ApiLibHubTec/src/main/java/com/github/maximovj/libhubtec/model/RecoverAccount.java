package com.github.maximovj.libhubtec.model;

import jakarta.persistence.Entity;
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
@Table(name = "recover_accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecoverAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String last_name;
    String email;
    String token;
    Integer code;
    Boolean active;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = true)
    Account account;

}
