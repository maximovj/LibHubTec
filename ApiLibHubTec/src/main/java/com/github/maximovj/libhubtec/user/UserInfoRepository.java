package com.github.maximovj.libhubtec.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	
	Optional<UserInfo> findByEmail(String email);
	Optional<UserInfo> findByUsername(String username);

}
