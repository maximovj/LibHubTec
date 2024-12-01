package com.github.maximovj.libhubtec.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity()
@Table(name = "books")
@Data // Genera getters, setters, toString, equals, y hashCode
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos
public class Book implements Serializable {

	private static final long serialVersionUID = 3128977427304268716L;
	
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String cover;
	private String title;
	private String author;
	private String summary;
	private String description;
	private Integer stock;
	private BigDecimal price;
	
	@Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

}
