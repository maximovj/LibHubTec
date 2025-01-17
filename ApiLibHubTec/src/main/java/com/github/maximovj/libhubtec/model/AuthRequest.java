package com.github.maximovj.libhubtec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest 
{
	
	private String email;
	private String username;
	private String password;
	private String token;
	
}
