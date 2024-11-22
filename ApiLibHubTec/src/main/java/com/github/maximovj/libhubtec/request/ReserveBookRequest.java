package com.github.maximovj.libhubtec.request;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class ReserveBookRequest {
    
    Long reserve_book_id = -1L;
    Long account_id = -1L;
    Long book_id = -1L;

    @NotEmpty(message = "El campo es obligatorio")
    @Size(min = 10, max = 60, message = "Entre 10 y 60 carácteres")
    String date_from;
    
    @NotEmpty(message = "El campo es obligatorio")
    @Size(min = 10, max = 60, message = "Entre 10 y 60 carácteres")
    String date_to;

}
