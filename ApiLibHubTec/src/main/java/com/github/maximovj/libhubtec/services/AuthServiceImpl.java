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
import com.github.maximovj.libhubtec.user.UserInfo;
import com.github.maximovj.libhubtec.user.UserInfoRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthServiceImpl {

    private final UserInfoRepository userInfoRepository;
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
            
            Optional<UserInfo> userInfo = this.userInfoRepository.findByEmail(auth.getEmail());

            if(userInfo != null)
            {
                authResponse.setResponse(new ApiResponse(
                    "Autenticación", 
                    "Usuario autenticado exitosamente", 
                    "/v1/auth/authenticate",
                    "POST", 
                    HttpStatus.OK.value(), 
                    "success", 
                    true));
                    
                    
                    token = this.jwtService.generateToken(userInfo.get().getId());
                    data.setToken(token);
                    data.setIs_valid(true);
            }
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
    public ResponseEntity<AuthResponse> refreshToken(AuthRequest auth) {
        log.info("AuthServiceImpl::refreshToken | Iniciando proceso");
        AuthResponse authResponse = new AuthResponse();
        AuthTokenData data = new AuthTokenData();
        String token = "";
        
        token = this.jwtService.refreshToken(auth.getToken());

        if(token != null) {
            authResponse.setResponse(new ApiResponse(
                "Autenticación", 
                "Token regenerado exitosamente", 
                "/v1/auth/refresh-token",
                "POST", 
                HttpStatus.OK.value(), 
                "success", 
                true));
            data.setRefresh_token(token);
            data.setIs_valid(true);
        } else {
            authResponse.setResponse(new ApiResponse(
                "Autenticación", 
                "Token inválido o expirado", 
                "/v1/auth/refresh-token",
                "POST", 
                HttpStatus.UNAUTHORIZED.value(), 
                "error", 
                false));
        }
        
        log.info("AuthServiceImpl::refreshToken | Proceso  finalizado");
        authResponse.setData(Optional.ofNullable(data));
        return ResponseEntity.ok(authResponse);
    }

    @Override
    public ResponseEntity<AuthResponse> verifyToken(HttpServletRequest request) {
        log.info("AuthServiceImpl::verifyToken | Iniciando proceso");
        AuthResponse authResponse = new AuthResponse();
        AuthTokenData data = new AuthTokenData();
        // Obtiene el token del encabezado Authorization
        String authHeader = request.getHeader("Authorization");
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // Elimina "Bearer " para obtener solo el token
        } else {
            authResponse.setResponse(new ApiResponse(
                "Autenticación",
                "Encabezado Authorization no encontrado o mal formado",
                "/v1/auth/refresh-token",
                "POST",
                HttpStatus.BAD_REQUEST.value(),
                "error",
                false));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authResponse);
        }
        
        if(this.jwtService.validateToken(token)) {
            String userId = this.jwtService.extractUsername(token);
            Optional<UserInfo> userInfo = this.userInfoRepository.findById(Long.valueOf(userId));

            if(this.jwtService.verifyTokenWithUsername(token, userInfo))
            {
                authResponse.setResponse(new ApiResponse(
                    "Autenticación", 
                    "Token regenerado exitosamente", 
                    "/v1/auth/refresh-token",
                    "POST", 
                    HttpStatus.OK.value(), 
                    "success", 
                    true));
                    
                    data.setToken(token);
                    data.setRefresh_token(this.jwtService.refreshToken(token));
                    data.setIs_valid(true);
            }
        } else {
            authResponse.setResponse(new ApiResponse(
                "Autenticación", 
                "Token inválido o expirado", 
                "/v1/auth/refresh-token",
                "POST", 
                HttpStatus.UNAUTHORIZED.value(), 
                "error", 
                false));
        }

        log.info("AuthServiceImpl::verifyToken | Proceso  finalizado");
        authResponse.setData(Optional.ofNullable(data));
        return ResponseEntity.ok(authResponse);
    }

}
