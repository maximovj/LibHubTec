package com.github.maximovj.libhubtec.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.libhubtec.request.RecoverAccountRequest;
import com.github.maximovj.libhubtec.response.RecoverAccountResponse;
import com.github.maximovj.libhubtec.services.IRecoverAccountServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/v1")
public class RecoverAccountRestController {

    @Autowired
    IRecoverAccountServiceImpl service;

    @PostMapping("/recover/account")
    public ResponseEntity<RecoverAccountResponse> recoverAccount(HttpServletRequest httpRequest, @RequestBody RecoverAccountRequest request) {
        return this.service.recoverAccount(httpRequest, request);
    }

    @PostMapping("/recover/verify-token")
    public ResponseEntity<RecoverAccountResponse> verifyToken(HttpServletRequest httpRequest) {
        return this.service.verifyToken(httpRequest);
    }
    
}
