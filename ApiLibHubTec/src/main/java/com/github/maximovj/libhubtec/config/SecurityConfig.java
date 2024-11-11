package com.github.maximovj.libhubtec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity  http) throws Exception {
		http
			.csrf( csrf -> csrf.disable())
			.authorizeHttpRequests( authRequest -> 
				authRequest
				.requestMatchers("/v1/auth/**").permitAll()
				.anyRequest().authenticated()
			);
		
		return http.build();
	}
}
