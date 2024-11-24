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
import com.github.maximovj.libhubtec.model.NotificationAccount;
import com.github.maximovj.libhubtec.model.NotificationAccountStatus;
import com.github.maximovj.libhubtec.response.ApiResponse;
import com.github.maximovj.libhubtec.response.NotificationAccountResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationAccountServiceImpl implements INotificationAccountServiceImpl {

    private final INotificacionAccountDao notificacionAccountDao;
    private final IAccountDao accountDao;
    private ApiResponse apiResponse;

    // Respuesta exitosa
    private ResponseEntity<NotificationAccountResponse> buildSuccessResponse(String content, Optional<List<NotificationAccount>> data) 
    {
        NotificationAccountResponse response = new NotificationAccountResponse();
        this.apiResponse.setCtx_title("Notificación de cuentas");
        this.apiResponse.setCtx_content(content);
        this.apiResponse.setCode(HttpStatus.OK.value());
        this.apiResponse.setSuccess(true);
        this.apiResponse.setStatus("success");
        response.setResponse(apiResponse);
        response.setData(data);
        return ResponseEntity.ok().body(response);
    }

    // Respuesta error
    private ResponseEntity<NotificationAccountResponse> buildErrorResponse(HttpStatus status, String content) 
    {
        NotificationAccountResponse response = new NotificationAccountResponse();
        this.apiResponse.setCtx_title("Notificación de cuentas");
        this.apiResponse.setCtx_content(content);
        this.apiResponse.setCode(status.value());
        this.apiResponse.setSuccess(false);
        this.apiResponse.setStatus("error");
        response.setResponse(apiResponse);
        response.setData(null);
        return ResponseEntity.status(status.value()).body(response);
    }

    @Override
    public ResponseEntity<NotificationAccountResponse> listNotifications(Long account_id) 
    {
        List<NotificationAccount> list = new ArrayList<>();
        this.apiResponse = new ApiResponse();
        this.apiResponse.setUri("/v1/notification/accounts/"+account_id+"/account/list");
        this.apiResponse.setType("GET");
        
        if(account_id == null) {
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Oops la cuenta es requerido");
        }

        Optional<Account> account = this.accountDao.findById(account_id);
        if(!account.isPresent())  {
            return this.buildErrorResponse(HttpStatus.NOT_FOUND, "Oops cuenta no existe en el sistema");       
        }

        list = this.notificacionAccountDao.findAllByAccount(account.get());
        return this.buildSuccessResponse("Lista obtenido exitosamente", Optional.ofNullable(list));
    }

    @Override
    public ResponseEntity<NotificationAccountResponse> markReadNotification(Long notificacion_account_id) 
    {
        List<NotificationAccount> list = new ArrayList<>();
        this.apiResponse = new ApiResponse();
        this.apiResponse.setUri("/v1/notification/accounts/"+notificacion_account_id+"/account/list");
        this.apiResponse.setType("GET");

        if(notificacion_account_id == null) {
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Oops la notificacion de cuenta es requerida");
        }

        Optional<NotificationAccount> notification_account = this.notificacionAccountDao.findById(notificacion_account_id);
        if(!notification_account.isPresent()) {
            return this.buildErrorResponse(HttpStatus.NOT_FOUND, "Oop la notificacion de cuenta no existe en el sistema");
        }

        notification_account.get().setStatus(NotificationAccountStatus.read);
        this.notificacionAccountDao.save(notification_account.get());
        list.add(notification_account.get());
        return this.buildSuccessResponse("Notificacion marca como leído correctamente", Optional.ofNullable(list));
    }
    
}
