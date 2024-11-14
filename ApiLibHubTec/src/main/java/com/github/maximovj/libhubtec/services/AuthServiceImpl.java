package com.github.maximovj.libhubtec.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.github.maximovj.libhubtec.model.AuthTokenData;
import com.github.maximovj.libhubtec.model.AuthRequest;
import com.github.maximovj.libhubtec.response.ApiResponse;
import com.github.maximovj.libhubtec.response.AuthResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthServiceImpl {

	private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<AuthResponse> authenticate(AuthRequest auth) {
        log.info("AuthServiceImpl::generateToken | Iniciando proceso");
        AuthResponse authResponse = new AuthResponse();
        AuthTokenData data = new AuthTokenData();
        String token = "";
        
        Authentication authentication = this.authenticationManager
				.authenticate( new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPassword()));

		if(authentication.isAuthenticated()) {
            authResponse.setResponse(new ApiResponse(
                "Autenticación", 
                "Usuario autenticado exitosamente", 
                "/v1/auth/authenticate",
                "POST", 
                HttpStatus.OK.value(), 
                "success", 
                true));
			token = this.jwtService.generateToken(auth.getEmail());
            data.setToken(token);
		} else {
            authResponse.setResponse(new ApiResponse(
                "Autenticación", 
                "Usuario no autenticado", 
                "/v1/auth/authenticate",
                "POST", 
                HttpStatus.UNAUTHORIZED.value(), 
                "error", 
                false));
			//throw new UsernameNotFoundException("Invalid user request!");
		}

        log.info("AuthServiceImpl::generateToken | Proceso  finalizado");
        authResponse.setData(Optional.ofNullable(data));
        return ResponseEntity.ok(authResponse);
    }
    
    @Override
    public ResponseEntity<AuthResponse> refreshToken() {
        log.info("AuthServiceImpl::generateToken | Iniciando proceso");
        AuthResponse authResponse = new AuthResponse();
        AuthTokenData data = new AuthTokenData();
        String token = "";
    
        authResponse = new AuthResponse();
        log.info("AuthServiceImpl::generateToken | Proceso  finalizado");
        return ResponseEntity.ok(authResponse);
    }

}
