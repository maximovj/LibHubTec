package com.github.maximovj.libhubtec.services;

import org.springframework.http.ResponseEntity;

import com.github.maximovj.libhubtec.request.RecoverAccountRequest;
import com.github.maximovj.libhubtec.response.RecoverAccountResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface IRecoverAccountServiceImpl {
    public ResponseEntity<RecoverAccountResponse> recoverAccount(HttpServletRequest httpRequest, RecoverAccountRequest request);
}
