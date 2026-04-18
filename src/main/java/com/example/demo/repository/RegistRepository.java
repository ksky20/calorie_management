package com.example.demo.repository;

import com.example.demo.entity.Food;
import com.example.demo.entity.Profile;
import com.example.demo.entity.User;

public interface RegistRepository {

	void addUserInfo(User user);
	
	void addProfile(Profile profile);
	
	void addFood(Food food);
	
	void editProfile(Profile profile);
	
	void editFood(Food food);
}
