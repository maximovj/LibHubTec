package com.github.maximovj.libhubtec.services;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.maximovj.libhubtec.dao.IAccountDao;
import com.github.maximovj.libhubtec.dao.IBookDao;
import com.github.maximovj.libhubtec.dao.ISearchDao;
import com.github.maximovj.libhubtec.model.Account;
import com.github.maximovj.libhubtec.model.Book;
import com.github.maximovj.libhubtec.model.Search;
import com.github.maximovj.libhubtec.response.ApiResponse;
import com.github.maximovj.libhubtec.response.BookResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookServiceImpl {

    private final ISearchDao searchDao;
    private final IAccountDao accountDao;
    private final JwtService jwtService;
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
            this.createNewSearch(httpRequest, "q", query_search, books);
        } else
        if(author != null &&  !author.isEmpty()) {
            books = (List<Book>) dao.findAllByAuthorLike(author);
            this.createNewSearch(httpRequest, "author", query_search, books);
        } else
        if(title != null &&  !title.isEmpty()) {
            books = (List<Book>) dao.findAllByTitleLike(title);
            this.createNewSearch(httpRequest, "title", query_search, books);
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

    private void createNewSearch(HttpServletRequest httpRequest, String _query, String _search, List<Book> books) 
    {
        Search search = new Search();
        String encodedSearch = "";
        String base_url = "";
        
        try {
            encodedSearch = URLEncoder.encode(_search, "UTF-8");
            base_url = "/books/list?"+_query+"="+encodedSearch;
        } catch (UnsupportedEncodingException e) {
            encodedSearch = "";
            base_url = "";
            e.printStackTrace();
        }

        String authorizationHeader = httpRequest.getHeader("Authorization");
        String token = null;
    
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            
            String userId = this.jwtService.extractUsername(token);
            Optional<Account> account = accountDao.findById(Long.valueOf(userId));
            if(account.isPresent() && base_url.length() > 16 &&  books.size() > 0 && encodedSearch.length() > 3) {

                Optional<Search> op_search = this.searchDao.findByAccountIdAndQueryAndSearch(account.get().getId(), _query, _search);
                
                if(op_search.isPresent()) {
                    op_search.get().setUpdated_at(LocalDateTime.now());
                    this.searchDao.save(op_search.get());
                } else {
                    search.setAccount_id(account.get().getId());
                    search.setSearch(_search);
                    search.setQuery(_query);
                    search.setBase_url(base_url);
                    search.setResult(books.size());
                    search.setCreated_at(LocalDateTime.now());
                    search.setUpdated_at(LocalDateTime.now());
                    log.info("Búsqueda guardado: ", search);
                    this.searchDao.save(search);
                }

            }
        }
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
