package com.github.maximovj.libhubtec.services;

import org.springframework.http.ResponseEntity;

import com.github.maximovj.libhubtec.request.ReserveBookRequest;
import com.github.maximovj.libhubtec.response.ReserveBookResponse;

public interface IReserveBookServiceImpl {

    public ResponseEntity<ReserveBookResponse> registerReserveBook(ReserveBookRequest request);

}
