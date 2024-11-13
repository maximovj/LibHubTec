package com.github.maximovj.libhubtec.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class AuthServiceImpl implements IAuthServiceImpl {

    private Logger log = LoggerFactory.getLogger(getClass());
    private AuthResponse authResponse;

    @Autowired
	private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<AuthResponse> authenticate(AuthRequest auth) {
        this.log.info("AuthServiceImpl::generateToken | Iniciando proceso");
        this.authResponse = new AuthResponse();
        String token = "";
        AuthTokenData data = new AuthTokenData();

        Authentication authentication = this.authenticationManager
				.authenticate( new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPassword()));

		if(authentication.isAuthenticated()) {
            this.authResponse.setResponse(new ApiResponse(
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
            this.authResponse.setResponse(new ApiResponse(
                "Autenticación", 
                "Usuario no autenticado", 
                "/v1/auth/authenticate",
                "POST", 
                HttpStatus.UNAUTHORIZED.value(), 
                "error", 
                false));
			//throw new UsernameNotFoundException("Invalid user request!");
		}

        this.log.info("AuthServiceImpl::generateToken | Proceso  finalizado");
        this.authResponse.setData(Optional.ofNullable(data));
        return ResponseEntity.ok(this.authResponse);
    }
    
    @Override
    public ResponseEntity<AuthResponse> refreshToken() {
        this.log.info("AuthServiceImpl::generateToken | Iniciando proceso");
        this.authResponse = new AuthResponse();
        this.log.info("AuthServiceImpl::generateToken | Proceso  finalizado");
        return ResponseEntity.ok(this.authResponse);
    }

}
