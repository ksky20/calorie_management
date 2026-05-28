package com.example.calorie.repository;

import com.example.calorie.entity.User;

public interface UserRepository {

	User selectByUsername(String username);

}
