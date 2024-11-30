package com.github.maximovj.libhubtec.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.maximovj.libhubtec.dao.IAccountDao;
import com.github.maximovj.libhubtec.dao.INotificacionAccountDao;
import com.github.maximovj.libhubtec.model.Account;
import com.github.maximovj.libhubtec.response.AccountResponse;
import com.github.maximovj.libhubtec.response.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountServiceImpl {

	private final INotificacionAccountDao notificacionAccountDao;
	private final IAccountDao iAccountDao;
	private AccountResponse response;
	private ApiResponse apiResponse;

	// Crear una respuesa exitosa
	private ResponseEntity<AccountResponse> buildSuccessResponse(String content, Optional<List<Account>> data, Long notifications)
	{
		AccountResponse response = new AccountResponse();
		this.apiResponse.setCtx_title("Cuenta");
		this.apiResponse.setCode(HttpStatus.OK.value());
		this.apiResponse.setStatus("success");
		this.apiResponse.setSuccess(true);
		this.apiResponse.setCtx_content(content);
		response.setResponse(apiResponse);
		response.setData(data);
		response.setNotifications(Long.valueOf(notifications));
		return ResponseEntity.ok(response);
	}

	// Crear una respuesa de error
	private ResponseEntity<AccountResponse> buildErrorResponse(HttpStatus status, String content)
	{
		AccountResponse response = new AccountResponse();
		this.apiResponse.setCtx_title("Cuenta");
		this.apiResponse.setCode(status.value());
		this.apiResponse.setStatus("success");
		this.apiResponse.setSuccess(true);
		this.apiResponse.setCtx_content(content);
		response.setResponse(apiResponse);
		response.setData(null);
		response.setNotifications(null);
		return ResponseEntity.status(status).body(response);
	}

	@Override
	public ResponseEntity<AccountResponse> FindAccountAll() {
		log.info("@FindAccountAll : Iniciando");
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
			
			log.error("@FindAccountAll : Error");
			e.getStackTrace();
		}
		
		this.response.setData(Optional.ofNullable(accounts));
		log.info("@FindAccountAll : Finalizado");
		return ResponseEntity.ok(this.response);
	}

	@Override
	public ResponseEntity<AccountResponse> getAccountDetails(Long id) {
		log.info("@getAccountDetails : Iniciando");
		List<Account> accounts =  new ArrayList<Account>();
		this.apiResponse = new ApiResponse();
		this.apiResponse.setUri("/v1/account/"+id+"/details");
		this.apiResponse.setType("GET");

		if(id == null) {
			return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Oops cuenta no proporcionada");
		}

		Optional<Account> account = this.iAccountDao.findById(id);
		if(!account.isPresent()) {
			return this.buildErrorResponse(HttpStatus.NOT_FOUND, "Oops cuenta no encontrado en el sistema");
		}


		Long notifications = this.notificacionAccountDao.countByAccount(account.get());
		accounts.add(account.get());
		log.info("@getAccountDetails : Finalizado");
		return this.buildSuccessResponse("Información de la cuenta proporcionada correctamente", Optional.ofNullable(accounts), notifications);
	}
	
}
