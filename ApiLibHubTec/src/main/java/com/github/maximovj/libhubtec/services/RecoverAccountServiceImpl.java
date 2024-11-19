package com.github.maximovj.libhubtec.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.maximovj.libhubtec.dao.IAccountDao;
import com.github.maximovj.libhubtec.dao.IRecoverAccountDao;
import com.github.maximovj.libhubtec.model.Account;
import com.github.maximovj.libhubtec.model.RecoverAccount;
import com.github.maximovj.libhubtec.request.RecoverAccountRequest;
import com.github.maximovj.libhubtec.response.ApiResponse;
import com.github.maximovj.libhubtec.response.RecoverAccountResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecoverAccountServiceImpl implements IRecoverAccountServiceImpl {

    private final IRecoverAccountDao recoverAccountDao;
    private final JwtService jwtService;
    private final IAccountDao accountDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ResponseEntity<RecoverAccountResponse> recoverAccount(HttpServletRequest httpRequest, RecoverAccountRequest request) {
        RecoverAccountResponse accountResponse = new RecoverAccountResponse();
        ApiResponse apiResponse;

        // 1. Validar el header de autorización y token
        String token = extractToken(httpRequest.getHeader("Authorization"));
        if (token == null) {
            return buildErrorResponse(accountResponse, "Cuenta recuperado fallida, token no incluida.", HttpStatus.UNAUTHORIZED);
        }

        if (!jwtService.validateToken(token)) {
            return buildErrorResponse(accountResponse, "Token inválido o caducado.", HttpStatus.UNAUTHORIZED);
        }

        // 2. Extraer usuario y recuperar datos
        String userId = jwtService.extractUsername(token);
        Optional<RecoverAccount> recoverAccountOpt = recoverAccountDao.findByAccountId(Long.valueOf(userId));
        Optional<Account> accountOpt = accountDao.findById(Long.valueOf(userId));

        if (recoverAccountOpt.isEmpty() || accountOpt.isEmpty()) {
            return buildErrorResponse(accountResponse, "No existe solicitud para recuperar cuenta.", HttpStatus.BAD_REQUEST);
        }

        RecoverAccount recoverAccount = recoverAccountOpt.get();
        Account account = accountOpt.get();

        // 3. Validar código, token y cuenta
        if (!isValidRecovery(recoverAccount, account, request, token)) {
            return buildErrorResponse(accountResponse, "Cuenta recuperado fallida, verifica su código, token o cuenta.", HttpStatus.BAD_REQUEST);
        }

        // 4. Validar contraseñas
        if (!isValidPasswords(request)) {
            return buildErrorResponse(accountResponse, "La nueva contraseña no es válida o no coinciden.", HttpStatus.BAD_REQUEST);
        }

        // 5. Actualizar estado y retornar éxito
        this.generateNewPassword(account, request);
        recoverAccountDao.delete(recoverAccount);
        return buildSuccessResponse(accountResponse, "Cuenta recuperada exitosamente.");
    }

    private void generateNewPassword(Account account, RecoverAccountRequest request) 
    {
        String _password = this.bCryptPasswordEncoder.encode(request.getConfirm_password());
        this.accountDao.updatePasswordById(account.getId(), _password);
    }

    private String extractToken(String authHeader) {
        return (authHeader != null && authHeader.startsWith("Bearer "))
                ? authHeader.substring(7)
                : null;
    }

    private boolean isValidRecovery(RecoverAccount recoverAccount, Account account, RecoverAccountRequest request, String token) {
        String claimCode = jwtService.extractClaim(token, "code");
        return recoverAccount.getAccount().equals(account) &&
               recoverAccount.getToken().equals(token) &&
               recoverAccount.getCode().equals(Integer.parseInt(request.getCode())) &&
               claimCode != null;
    }

    private boolean isValidPasswords(RecoverAccountRequest request) {
        return request.getNew_password() != null &&
               request.getConfirm_password() != null &&
               request.getNew_password().equals(request.getConfirm_password());
    }

    private ResponseEntity<RecoverAccountResponse> buildErrorResponse(RecoverAccountResponse response, String message, HttpStatus status) {
        response.setResponse(new ApiResponse(
                "Recuperación de cuenta",
                message,
                "/v1/recover/account",
                "POST",
                status.value(),
                "error",
                false));
        return ResponseEntity.status(status).body(response);
    }

    private ResponseEntity<RecoverAccountResponse> buildSuccessResponse(RecoverAccountResponse response, String message) {
        response.setResponse(new ApiResponse(
                "Recuperación de cuenta",
                message,
                "/v1/recover/account",
                "POST",
                HttpStatus.OK.value(),
                "success",
                true));
        return ResponseEntity.ok(response);
    }
    
}

