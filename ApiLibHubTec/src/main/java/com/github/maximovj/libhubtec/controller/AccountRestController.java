package com.github.maximovj.libhubtec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.libhubtec.response.AccountResponse;
import com.github.maximovj.libhubtec.services.IAccountServiceImpl;

@RestController()
@RequestMapping("/v1")
public class AccountRestController {
	
	@Autowired
	private IAccountServiceImpl service;

	@GetMapping("/accounts")
	public ResponseEntity<AccountResponse> FindAccountAll() {
		return this.service.FindAccountAll();
	}

	@GetMapping("/accounts/{id}/details")
	public ResponseEntity<AccountResponse> getAccountDetails(@PathVariable Long id) {
		return this.service.getAccountDetails(id);
	}
	
}
