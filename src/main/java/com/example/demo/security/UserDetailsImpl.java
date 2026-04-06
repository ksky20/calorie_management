package com.example.demo.security;

import java.util.Collection;
import java.util.Collections;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.User;

public class UserDetailsImpl implements UserDetails {

	private final String username;
	private final String password;
	private final Integer id;
	
	
	public UserDetailsImpl(User user) {
		// デバッグ用
//	    System.out.println("Userの中身: " + user.getId() + ", " + user.getUsername());	
		username = user.getUsername();
		password = user.getPassword();
		id = user.getId();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public @Nullable String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public Integer getId() {
		return id;
	}

}
