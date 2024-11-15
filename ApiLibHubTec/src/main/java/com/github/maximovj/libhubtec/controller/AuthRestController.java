package com.github.maximovj.libhubtec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.libhubtec.model.AuthRequest;
import com.github.maximovj.libhubtec.response.AuthResponse;
import com.github.maximovj.libhubtec.services.IAuthServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("/v1")
public class AuthRestController {

	@Autowired
	private IAuthServiceImpl service; 

	
	@PostMapping("/auth/authenticate")
	public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest auth)
	{
		return this.service.authenticate(auth);
	}

	@PostMapping("/auth/refresh-token")
	public ResponseEntity<AuthResponse> refreshToken(@RequestBody AuthRequest auth) {
		return this.service.refreshToken(auth);
	}
	
	@GetMapping("/auth/verify-token")
	public ResponseEntity<AuthResponse> verifyToken(HttpServletRequest request) {
		return this.service.verifyToken(request);
	}
	

}
