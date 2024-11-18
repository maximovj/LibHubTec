package com.github.maximovj.libhubtec.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity()
@Table(name = "accounts")
@Data // Genera getters, setters, toString, equals, y hashCode
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos
public class Account implements Serializable {

	private static final long serialVersionUID = 4701381234928182250L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String name;
	String last_name;
	String sex;
	String age;
	String grade;
	String shift;
	String photo;
	String bio;
	String username;
	String email;
	String password;
	
}
