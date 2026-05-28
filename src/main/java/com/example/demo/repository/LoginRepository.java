package com.example.demo.repository;

import com.example.demo.entity.Profile;

public interface LoginRepository {

	Profile findByUserId(int userId);
}
