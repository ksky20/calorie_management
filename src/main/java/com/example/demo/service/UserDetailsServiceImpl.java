package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// ① メソッドが呼ばれているか確認
	    System.out.println("loadUserByUsername呼ばれた: " + username);

		
		User user = userRepository.selectByUsername(username);
		
		 // ② DBからユーザーが取得できているか確認
	    System.out.println("取得したuser: " + user);
		
		if(user == null) {
			throw new UsernameNotFoundException("ユーザが存在しない");
		}
		
		return new UserDetailsImpl(user);
	}

}
