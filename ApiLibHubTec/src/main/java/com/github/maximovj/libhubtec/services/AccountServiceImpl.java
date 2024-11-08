package com.github.maximovj.libhubtec.services;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.maximovj.libhubtec.dao.IAccountDao;
import com.github.maximovj.libhubtec.model.Account;
import com.github.maximovj.libhubtec.response.AccountResponse;
import com.github.maximovj.libhubtec.response.ApiResponse;

@Service
public class AccountServiceImpl implements IAccountServiceImpl {
	
	private AccountResponse response;
	
	@Autowired
	private IAccountDao iAccountDao;

	@Override
	public ResponseEntity<AccountResponse> FindAccountAll() {
		List<Account> accounts =  new ArrayList<Account>();
		
		this.response = new AccountResponse();
		
		try {
			this.response.setResponse(new ApiResponse(
					"Listar cuentas", 
					"Cuentas listados correctamente", 
					"/v1/accounts", 
					"GET", 
					HttpStatus.OK.value(), 
					"success", 
					true));
			
			accounts = (List<Account>) iAccountDao.findAll();
			this.response.setData(Optional.ofNullable(accounts));			
		} catch (Exception e) {
			
			this.response.setResponse(new ApiResponse(
					"Listar cuentas", 
					"Error al obtener las cuentas", 
					"/v1/accounts", 
					"GET", 
					HttpStatus.NO_CONTENT.value(), 
					"error", 
					false));
			
			this.response.setData(Optional.ofNullable(accounts));
			e.getStackTrace();
			System.out.println("AccountServiceImpl::FindAccountAll | ** Error **");
			System.out.println(e.getMessage());
		}
		
		return ResponseEntity.ok(this.response);
	}
	
}
