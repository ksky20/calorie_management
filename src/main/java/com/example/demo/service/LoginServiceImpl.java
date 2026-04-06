package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Profile;
import com.example.demo.repository.LoginRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final LoginRepository repository;
	
	@Override
	public Profile findUserByUserId(int userId) {
		
		return repository.findByUserId(userId);
	}

}
