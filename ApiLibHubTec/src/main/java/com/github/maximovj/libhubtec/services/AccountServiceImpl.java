package com.github.maximovj.libhubtec.services;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private Logger log = LoggerFactory.getLogger(getClass());
	private AccountResponse response;
	
	@Autowired
	private IAccountDao iAccountDao;

	@Override
	public ResponseEntity<AccountResponse> FindAccountAll() {
		this.log.info("@FindAccountAll : Iniciando");
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
						
		} catch (Exception e) {
			
			this.response.setResponse(new ApiResponse(
					"Listar cuentas", 
					"Error al obtener las cuentas", 
					"/v1/accounts", 
					"GET", 
					HttpStatus.NO_CONTENT.value(), 
					"error", 
					false));
			
			this.log.error("@FindAccountAll : Error");
			e.getStackTrace();
		}
		
		this.response.setData(Optional.ofNullable(accounts));
		this.log.info("@FindAccountAll : Finalizado");
		return ResponseEntity.ok(this.response);
	}
	
}
