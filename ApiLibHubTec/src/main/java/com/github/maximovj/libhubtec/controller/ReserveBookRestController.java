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
    
}
