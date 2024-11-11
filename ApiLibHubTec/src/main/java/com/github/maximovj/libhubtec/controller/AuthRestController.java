package com.github.maximovj.libhubtec.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.libhubtec.model.Auth;
import com.github.maximovj.libhubtec.request.LoginRequest;
import com.github.maximovj.libhubtec.response.AuthResponse;
import com.github.maximovj.libhubtec.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController()
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AuthRestController {
	
	private final AuthService authService;
	
	@PostMapping("/auth/authenticate")
	public ResponseEntity<AuthResponse> authenticate(@RequestBody LoginRequest request) 
	{
		return ResponseEntity.ok(authService.login(request));
	}
	
	@PostMapping("/auth/register")
	public ResponseEntity<AuthResponse> register(@RequestBody LoginRequest request) 
	{
		return ResponseEntity.ok(authService.register(request));
	}

}
