package com.example.calorie.repository;

import com.example.calorie.entity.FoodList;
import com.example.calorie.entity.User;
import com.example.calorie.entity.UserProfile;

public interface RegistRepository {

	//ユーザ情報を登録
	void insertUser(User user);

	//UserProfileを登録
	UserProfile registUser(UserProfile userProfile);

	//FoodListを登録
	FoodList insertFood(FoodList foodList);
}
