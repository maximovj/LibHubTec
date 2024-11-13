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
import com.github.maximovj.libhubtec.user.UserInfoService;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	
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
	            username = this.jwtService.extractUsername(token); // Extraer username
	        }
	       
	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails = this.userInfoService.loadUserByUsername(username);

	            if (jwtService.validateToken(token, userDetails)) {
	                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
	                    userDetails,
	                    null,
	                    userDetails.getAuthorities()
	                );
	                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authToken);
	            }
	        }

	        // Continue the filter chain
	        filterChain.doFilter(request, response);
		
	}

}
