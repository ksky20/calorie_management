package com.example.demo.service;

import com.example.demo.entity.Food;
import com.example.demo.entity.Profile;
import com.example.demo.entity.User;

public interface RegistService {

	void registUser(User user);
	
	void registProfile(Profile profile);
	
	void registFood(Food food);
	
	void editProfile(Profile profile);
	
	void editFood(Food food);
}
