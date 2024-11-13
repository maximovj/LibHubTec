package com.github.maximovj.libhubtec.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfoDetails implements UserDetails {
	
	private static final long serialVersionUID = -2410551617683618347L;
	private String username;
	private String password;
	//private List<GrantedAuthority> authorities;
	
	public UserInfoDetails(UserInfo userInfo) {
		this.username = userInfo.getEmail(); // NOTE: Asumiendo que `email` es usado como username
		this.password = userInfo.getPassword();
		//this.authorities = List.of(userInfo.getRoles().split(","))
        //        .stream()
        //        .map(SimpleGrantedAuthority::new)
        //        .collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO: Auto-generated method stub
		return null;
	}
	
	@Override
	public String getPassword() {
		// TODO: Auto-generated method stub
		return this.password;
	}
	
	@Override
	public String getUsername() {
		// TODO: Auto-generated method stub
		return this.username;
	}
	
	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
	
}
