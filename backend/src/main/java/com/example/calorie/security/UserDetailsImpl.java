package com.example.calorie.security;

import java.util.Collection;
import java.util.Collections;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.calorie.entity.User;

// loadUserByUsernameの戻り値
public class UserDetailsImpl implements UserDetails {

	private final Long id;
	private final String username;
	private final String password;

	public UserDetailsImpl(User user) {

		id = user.getId();
		username = user.getUsername();
		password = user.getPassword();
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

	public Long getId() {

		return id;
	}

}
