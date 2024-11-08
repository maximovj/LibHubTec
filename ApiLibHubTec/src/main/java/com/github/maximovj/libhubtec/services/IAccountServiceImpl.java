package com.github.maximovj.libhubtec.services;

import org.springframework.http.ResponseEntity;
import com.github.maximovj.libhubtec.response.AccountResponse;

public interface IAccountServiceImpl {
	
	public ResponseEntity<AccountResponse> FindAccountAll();

}
