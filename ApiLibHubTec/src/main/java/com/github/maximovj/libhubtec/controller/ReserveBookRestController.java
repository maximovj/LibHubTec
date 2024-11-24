package com.github.maximovj.libhubtec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.libhubtec.request.ReserveBookRequest;
import com.github.maximovj.libhubtec.response.ReserveBookResponse;
import com.github.maximovj.libhubtec.services.IReserveBookServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/v1")
public class ReserveBookRestController implements IReserveBookServiceImpl {

    @Autowired
    private IReserveBookServiceImpl bookServiceImpl;

    @PostMapping("/reserve/book/register")
    @Override
    public ResponseEntity<ReserveBookResponse> registerReserveBook(@RequestBody @Valid ReserveBookRequest request) {
        return this.bookServiceImpl.registerReserveBook(request);
    }

    @DeleteMapping("/reserve/book/cancel")
    @Override
    public ResponseEntity<ReserveBookResponse> cancelReserveBook(@RequestBody @Valid ReserveBookRequest request) {
        return this.bookServiceImpl.cancelReserveBook(request);
    }

    @GetMapping("/reserve/book/list/{account_id}/account")
    @Override
    public ResponseEntity<ReserveBookResponse> listReserveBook(@PathVariable Long account_id) {
        return this.bookServiceImpl.listReserveBook(account_id);
    }

    @GetMapping("/reserve/book/find/{book_id}/account/{account_id}")
    @Override
    public ResponseEntity<ReserveBookResponse> findReserveBook(@PathVariable Long book_id, @PathVariable Long account_id) {
       return this.bookServiceImpl.findReserveBook(book_id, account_id);
    }
    
}
