package com.github.maximovj.libhubtec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.github.maximovj.libhubtec.filter.JwtAuthFilter;
import com.github.maximovj.libhubtec.user.UserInfoService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	@Bean
	public UserDetailsService userDetailsService(){ 
		return new UserInfoService();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Encriptación de contraseña usando BCrypt
    }
	
	@Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity  http) throws Exception {
		http
			.cors( cors -> cors.configure(http) )
			.csrf( csrf -> csrf.disable())
			.authorizeHttpRequests( authRequest -> 
				authRequest
				.requestMatchers("/v1/auth/**").permitAll()
				.anyRequest().authenticated()
			)
			.sessionManagement(sess -> sess
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No sessions
	        )
			.authenticationProvider(authenticationProvider()) // Custom authentication provider
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter
		
		return http.build();
	}
}
