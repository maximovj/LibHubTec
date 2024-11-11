package com.github.maximovj.libhubtec.services;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.maximovj.libhubtec.request.LoginRequest;
import com.github.maximovj.libhubtec.response.AuthResponse;
import com.github.maximovj.libhubtec.user.Role;
import com.github.maximovj.libhubtec.user.User;
import com.github.maximovj.libhubtec.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UserRepository userRepository; 
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationProvider authenticationProvider;
	
	public AuthResponse login(LoginRequest request) {
		authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
		String token = jwtService.getToken(user);
		return AuthResponse
				.builder()
				.token(token)
				.build();
	} 
	
	public AuthResponse register(LoginRequest request) {
		User user = User.builder()
				.username(request.getUsername())
				.email(request.getEmail())
				.password(this.passwordEncoder.encode(request.getPassword()))
				.role(Role.USER)
				.build();
		
		userRepository.save(user);
		return AuthResponse
				.builder()
				.token(this.jwtService.getToken(user))
				.build();
	} 


}
