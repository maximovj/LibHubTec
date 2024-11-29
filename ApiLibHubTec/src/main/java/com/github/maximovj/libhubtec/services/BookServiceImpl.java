package com.github.maximovj.libhubtec.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.maximovj.libhubtec.dao.IBookDao;
import com.github.maximovj.libhubtec.model.Book;
import com.github.maximovj.libhubtec.response.ApiResponse;
import com.github.maximovj.libhubtec.response.BookResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookServiceImpl {

    private final IBookDao dao;
    private ApiResponse apiResponse;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<BookResponse> findAllBooks() {
        log.info("@findAllBooks : Iniciando");
        List<Book> books = new ArrayList<>();
        this.apiResponse = new ApiResponse();
        this.apiResponse.setUri("/v1/books");
        this.apiResponse.setType("GET");
        
        try {
            books = (List<Book>) dao.findAll(Sort.by("updatedAt"));
        } catch (Exception e) {
            return this.buildErrorResponse("Erro al obtener la lista de libros", HttpStatus.BAD_REQUEST);
        }

        log.info("@findAllBooks : Finalizado");
        return this.buildSuccessResponse("Lista de libros listando correctamente", Optional.of(books));
    }

    @Override
    public ResponseEntity<BookResponse> searchBooks(HttpServletRequest httpRequest) {
        log.info("@searchBooks : Iniciando");
        List<Book> books = new ArrayList<>();
        this.apiResponse = new ApiResponse();
        this.apiResponse.setUri("/v1/books/search");
        this.apiResponse.setType("GET");

        String query_search = httpRequest.getParameter("q");
        String title = httpRequest.getParameter("title");
        String author = httpRequest.getParameter("author");

        if(query_search != null &&  !query_search.isEmpty()) {
            books = (List<Book>) dao.searchByKeyword(query_search);   
        } else
        if(author != null &&  !author.isEmpty()) {
            books = (List<Book>) dao.findAllByAuthorLike(author);
        } else
        if(title != null &&  !title.isEmpty()) {
            books = (List<Book>) dao.findAllByTitleLike(title);
        } else {
            books = (List<Book>) dao.findAll(Sort.by("updatedAt"));       
        }
        
        log.info("@searchBooks : Finalizado");
        return this.buildSuccessResponse("Resultado de busqueda obtenido correctamente", Optional.of(books));
    }

    @Override
    public ResponseEntity<BookResponse> findBookById(Long id) {
        log.info("findBookById | iniciando");
        List<Book> books = new ArrayList<>();
        this.apiResponse = new ApiResponse();
        this.apiResponse.setUri("/v1/books/"+id+"/books-details");
        this.apiResponse.setType("GET");

        try {
            Optional<Book> book = this.dao.findById(id);
            books.add(book.get());
        } catch (Exception e) {

            System.out.println(e.getMessage());
            log.info("findBookById | error");
            return this.buildErrorResponse("Oops hubo un error al intentar obtener la información del libro", HttpStatus.NOT_FOUND);
        }

        log.info("findBookById | finalizado");
        return this.buildSuccessResponse("Información del libro obtenido correctamente", Optional.of(books));
    }
    
    private ResponseEntity<BookResponse> buildSuccessResponse(String msg, Optional<List<Book>> data)
    {
        BookResponse response = new BookResponse();
        this.apiResponse.setCtx_title("Libros");
        this.apiResponse.setCtx_content(msg);
        this.apiResponse.setCode(HttpStatus.OK.value());
        this.apiResponse.setSuccess(true);
        this.apiResponse.setStatus("success");
        response.setResponse(apiResponse);
        response.setData(data);
        return ResponseEntity.ok(response);
    }

    private ResponseEntity<BookResponse> buildErrorResponse(String msg, HttpStatus httpStatus)
    {
        BookResponse response = new BookResponse();
        this.apiResponse.setCtx_title("Libros");
        this.apiResponse.setCtx_content(msg);
        this.apiResponse.setCode(httpStatus.value());
        this.apiResponse.setSuccess(true);
        this.apiResponse.setStatus("success");
        response.setResponse(apiResponse);
        response.setData(null);
        return ResponseEntity.status(httpStatus).body(response);
    }

}
