package com.github.maximovj.libhubtec.model;

import java.io.Serializable;

public class Auth implements Serializable {
	
	private static final long serialVersionUID = -2227689832756794685L;

	private String email;
	
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
