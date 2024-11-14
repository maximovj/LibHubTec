package com.github.maximovj.libhubtec.services;

import org.springframework.http.ResponseEntity;

import com.github.maximovj.libhubtec.model.AuthRequest;
import com.github.maximovj.libhubtec.response.AuthResponse;

public interface IAuthServiceImpl {

    public ResponseEntity<AuthResponse> authenticate(AuthRequest auth);
    public ResponseEntity<AuthResponse> refreshToken(AuthRequest auth);
    public ResponseEntity<AuthResponse> verifyToken(AuthRequest auth);

}
