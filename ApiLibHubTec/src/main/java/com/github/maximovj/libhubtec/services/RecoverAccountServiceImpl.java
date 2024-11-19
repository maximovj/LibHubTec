package com.github.maximovj.libhubtec.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final EmailService emailService;
    private RecoverAccountResponse response;
    private Optional<RecoverAccount> recoverAccountOpt;
    private Optional<Account> accountOpt;
    private String token;

    @Override
    public ResponseEntity<RecoverAccountResponse> recoverAccount(HttpServletRequest httpRequest, RecoverAccountRequest request) {
        this.validateTokenAndRecoverAccount(httpRequest);

        if (this.recoverAccountOpt.isEmpty() || this.accountOpt.isEmpty()) {
            return buildErrorResponse("No existe solicitud para recuperar cuenta.", HttpStatus.BAD_REQUEST);
        }

        RecoverAccount recoverAccount = this.recoverAccountOpt.get();
        Account account = this.accountOpt.get();

        // 3. Validar código, token y cuenta
        if (!isValidRecovery(recoverAccount, account, request, this.token)) {
            return buildErrorResponse("Cuenta recuperado fallida, verifica su código, token o cuenta.", HttpStatus.BAD_REQUEST);
        }

        // 4. Validar contraseñas
        if (!isValidPasswords(request)) {
            return buildErrorResponse("La nueva contraseña no es válida o no coinciden.", HttpStatus.BAD_REQUEST);
        }

        // 5. Actualizar estado y retornar éxito
        this.generateNewPassword(account, request);
        recoverAccountDao.delete(recoverAccount);
        this.emailService.sendEmailRecoverAccountSuccess(account);
        return buildSuccessResponse("Cuenta recuperada exitosamente.");
    }

    @Override
    public ResponseEntity<RecoverAccountResponse> verifyToken(HttpServletRequest httpRequest) {
        return this.validateTokenAndRecoverAccount(httpRequest);
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

    private ResponseEntity<RecoverAccountResponse> buildErrorResponse(String message, HttpStatus status) {
        this.response = new RecoverAccountResponse();
        this.response.setResponse(new ApiResponse(
                "Recuperación de cuenta",
                message,
                "/v1/recover/account",
                "POST",
                status.value(),
                "error",
                false));
        return ResponseEntity.status(status).body(this.response);
    }

    private ResponseEntity<RecoverAccountResponse> buildSuccessResponse(String message) {
        this.response = new RecoverAccountResponse();
        this.response.setResponse(new ApiResponse(
                "Recuperación de cuenta",
                message,
                "/v1/recover/account",
                "POST",
                HttpStatus.OK.value(),
                "success",
                true));
        return ResponseEntity.ok(this.response);
    }

    private ResponseEntity<RecoverAccountResponse> validateTokenAndRecoverAccount(HttpServletRequest httpRequest) 
    {
        // 1. Validar el header de autorización y token
        this.token = extractToken(httpRequest.getHeader("Authorization"));
        if (this.token == null) {
            return buildErrorResponse("Cuenta recuperado fallida, token no incluida.", HttpStatus.UNAUTHORIZED);
        }

        if (!jwtService.validateToken(this.token)) {
            return buildErrorResponse("Token inválido o caducado.", HttpStatus.UNAUTHORIZED);
        }

        // 2. Extraer usuario y recuperar datos
        String userId = jwtService.extractUsername(this.token); // Extraer "sub" claim
        this.recoverAccountOpt = recoverAccountDao.findByAccountId(Long.valueOf(userId));
        this.accountOpt = accountDao.findById(Long.valueOf(userId));

        if (this.recoverAccountOpt.isEmpty() || this.accountOpt.isEmpty()) {
            return buildErrorResponse("No existe solicitud para recuperar cuenta.", HttpStatus.BAD_REQUEST);
        }

        return this.buildSuccessResponse("Token verificado con éxito.");
    }
    
}

