package com.example.demo.service;

import com.example.demo.entity.Profile;

public interface LoginService {

	Profile findUserByUserId(int userId);
}
