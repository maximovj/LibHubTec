package com.github.maximovj.libhubtec.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.github.maximovj.libhubtec.services.JwtService;
import com.github.maximovj.libhubtec.user.UserInfo;
import com.github.maximovj.libhubtec.user.UserInfoRepository;
import com.github.maximovj.libhubtec.user.UserInfoService;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserInfoRepository  userInfoRepository;

	@Autowired
	private JwtService jwtService; 
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			// Obtenemos  `Authorization` de la cabecera
	        String authHeader = request.getHeader("Authorization");
	        String token = null;
	        String username = null;
	
	        // Verificamos que inicie con `Bearer `
	        if (authHeader != null && authHeader.startsWith("Bearer ")) {
	            token = authHeader.substring(7); // Extraer token
				// Válida si el token puede extraer los claims
				if(jwtService.validateToken(token)){
					username = this.jwtService.extractUsername(token); // Extraer subject (username / id)
					Optional<UserInfo> userInfo = this.userInfoRepository.findById(Long.valueOf(username)); 
	       
					// Verificar si no existe en usuario y si no existe una autententicación incializada
					if (userInfo != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails userDetails = this.userInfoService.loadUserByUsername(userInfo.get().getEmail());
						
						// Verifica si el token tiene el mismo subject (username / id)
						if (jwtService.verifyTokenWithUsername(token, userInfo)) {
							UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
								userDetails,
								null,
								userDetails.getAuthorities()
							);
							authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
							SecurityContextHolder.getContext().setAuthentication(authToken);
						}
					}
				}
	        }

	        filterChain.doFilter(request, response);
		
	}

}
