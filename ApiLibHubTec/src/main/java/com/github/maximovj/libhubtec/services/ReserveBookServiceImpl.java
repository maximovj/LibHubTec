package com.github.maximovj.libhubtec.services;

import java.math.BigDecimal;
import java.net.http.WebSocket.Listener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;

import com.github.maximovj.libhubtec.dao.IAccountDao;
import com.github.maximovj.libhubtec.dao.IBookDao;
import com.github.maximovj.libhubtec.dao.IReserveBookDao;
import com.github.maximovj.libhubtec.model.Account;
import com.github.maximovj.libhubtec.model.Book;
import com.github.maximovj.libhubtec.model.ReserveBook;
import com.github.maximovj.libhubtec.model.ReserveBookStatus;
import com.github.maximovj.libhubtec.request.ReserveBookRequest;
import com.github.maximovj.libhubtec.response.ApiResponse;
import com.github.maximovj.libhubtec.response.ReserveBookResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReserveBookServiceImpl implements IReserveBookServiceImpl {

    private final IReserveBookDao reserveBookDao;
    private final IBookDao bookDao;
    private final IAccountDao accountDao;
    private ApiResponse apiResponse;
    private Optional<Account> account;
    private Optional<Book> book;

    @Override
    public ResponseEntity<ReserveBookResponse> registerReserveBook(ReserveBookRequest request) {
        log.info("registerReserveBook | Iniciando");
        this.defineApiResponse("/v1/reserve/book/register", "POST");
        List<ReserveBook> list = new ArrayList<>();

        this.validateAccountAndBook(request);
        if(!this.book.isPresent() || !this.account.isPresent())
        {
            return this.buildErrorResponse(HttpStatus.NOT_FOUND, "Oops cuenta o libro no encontrado en el sistema", null);
        }

        ReserveBook reserveBook = this.defineReserveBook(this.account.get(), this.book.get());
        reserveBook.setDate_from(LocalDate.parse(request.getDate_from()).atStartOfDay());
        reserveBook.setDate_to(LocalDate.parse(request.getDate_to()).atStartOfDay());
        list.add(reserveBook);
        this.reserveBookDao.save(reserveBook);

        log.info("registerReserveBook | Finalizado");
        return this.buildSuccessResponse(HttpStatus.CREATED, "Libro reservado exitosamente", Optional.ofNullable(list));
    }

    private ReserveBook defineReserveBook(Account account, Book book) 
    {
        ReserveBook reserveBook = new ReserveBook();
        reserveBook.setAccount(account);
        reserveBook.setAccount_name(account.getName());
        reserveBook.setAccount_last_name(account.getLast_name());
        reserveBook.setAccount_username(account.getUsername());
        reserveBook.setAccount_email(account.getEmail());

        reserveBook.setBook(book);
        reserveBook.setBook_author(book.getAuthor());
        reserveBook.setBook_title(book.getAuthor());
        reserveBook.setBook_count(Integer.valueOf(1));
        reserveBook.setBook_price(BigDecimal.valueOf(0));

        reserveBook.setDate_from(LocalDateTime.now());
        reserveBook.setDate_to(LocalDateTime.now());
        reserveBook.setStatus(ReserveBookStatus.pending);
        reserveBook.setActive(Boolean.valueOf(true));
        return reserveBook;
    }

    private void defineApiResponse(String uri, String type) {
        this.apiResponse = new ApiResponse();
        this.apiResponse.setCtx_title("Reservación de libros");
        this.apiResponse.setUri(uri);
        this.apiResponse.setType(type);
    };

    // Success => 200, 201, 203
    private ResponseEntity<ReserveBookResponse> buildSuccessResponse(HttpStatus status, String content, Optional<List<ReserveBook>> data){
        ReserveBookResponse _response = new ReserveBookResponse();
        this.apiResponse.setCtx_content(content);
        this.apiResponse.setCode(status.value());
        this.apiResponse.setSuccess(true);
        this.apiResponse.setStatus("success");
        _response.setResponse(apiResponse);
        _response.setData(data);
        return ResponseEntity.status(status).body(_response);
    }

    // Error => 402, 403, 404
    private ResponseEntity<ReserveBookResponse> buildErrorResponse(HttpStatus status, String content, Optional<List<ReserveBook>> data){
        ReserveBookResponse _response = new ReserveBookResponse();
        apiResponse.setCtx_content(content);
        apiResponse.setCode(status.value());
        apiResponse.setSuccess(false);
        apiResponse.setStatus("error");
        _response.setResponse(apiResponse);
        _response.setData(data);
        return ResponseEntity.status(status).body(_response);
    }

    private ResponseEntity<ReserveBookResponse> validateAccountAndBook(ReserveBookRequest request)
    {
        this.account = this.accountDao.findById(request.getAccount_id());
        this.book = this.bookDao.findById(request.getBook_id());

        if(!book.isPresent() || !account.isPresent())
        {
            return this.buildErrorResponse(HttpStatus.NOT_FOUND, "Oops cuenta o libro no encontrado en el sistema", null);
        }

        return this.buildSuccessResponse(HttpStatus.FOUND, "La cuenta y libro son válidos", null);
    }

}
