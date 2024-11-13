package com.github.maximovj.libhubtec.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements UserDetailsService {
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	//@Autowired
	//private PasswordEncoder passwordEncoder; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserInfo> userDetail = this.userInfoRepository.findByEmail(username); // TODO: Asumiendo que `email` es usado como username
		
		return userDetail
				.map(UserInfoDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found " + username));	
	}
	
	/*
	public String addUser(UserInfo userInfo) {
        // Encode password before saving the user
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "User Added Successfully";
    }
    */

}
