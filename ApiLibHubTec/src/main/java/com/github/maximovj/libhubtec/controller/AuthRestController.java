package com.github.maximovj.libhubtec.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.libhubtec.model.Auth;

@RestController()
@RequestMapping("/v1")
public class AuthRestController {
	
	@PostMapping("/auth/authenticate")
	public String authenticate(@RequestBody Auth auth) 
	{
		return auth.toString();
	}

}
