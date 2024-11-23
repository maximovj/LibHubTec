package com.github.maximovj.libhubtec.services;

import org.springframework.http.ResponseEntity;

import com.github.maximovj.libhubtec.request.ReserveBookRequest;
import com.github.maximovj.libhubtec.response.ReserveBookResponse;

public interface IReserveBookServiceImpl {

    public ResponseEntity<ReserveBookResponse> registerReserveBook(ReserveBookRequest request);

    public ResponseEntity<ReserveBookResponse> cancelReserveBook(ReserveBookRequest request);

    public ResponseEntity<ReserveBookResponse> listReserveBook(Long account_id);

    public ResponseEntity<ReserveBookResponse> findReserveBook(Long book_id, Long account_id);

}
