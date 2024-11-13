package com.github.maximovj.libhubtec.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.maximovj.libhubtec.dao.IBookDao;
import com.github.maximovj.libhubtec.model.Book;
import com.github.maximovj.libhubtec.response.ApiResponse;
import com.github.maximovj.libhubtec.response.BookResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookServiceImpl {

    private final IBookDao dao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<BookResponse> findAllBooks() {
        log.info("@findAllBooks : Iniciando");
        
        BookResponse response = new BookResponse();
        
        try {
            response.setResponse(new ApiResponse(
                "Listar libros", 
                "Cuentas listados correctamente", 
                "/v1/books", 
                "GET", 
                HttpStatus.OK.value(), 
                "success", 
                true
            ));

            List<Book> books = (List<Book>) dao.findAll();
            response.setData(Optional.ofNullable(books));
        } catch (Exception e) {
            response.setResponse(new ApiResponse(
                "Listar libros", 
                "Error al obtener los libros", 
                "/v1/books", 
                "GET", 
                HttpStatus.NO_CONTENT.value(), 
                "error", 
                false
            ));
            
            log.error("@findAllBooks : Error", e);
        }

        log.info("@findAllBooks : Finalizado");
        return ResponseEntity.ok(response);
    }
}
