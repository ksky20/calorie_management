package com.example.calorie.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.calorie.entity.User;
import com.example.calorie.repository.UserRepository;
import com.example.calorie.security.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.selectByUsername(username);

		if(user == null) {

			throw new UsernameNotFoundException("ユーザが存在しません");
		}

		return new UserDetailsImpl(user);
	}

}
