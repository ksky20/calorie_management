package com.example.calorie.service.biz;

import com.example.calorie.dto.response.FoodListResponse;
import com.example.calorie.dto.response.UserProfileResponse;
import com.example.calorie.entity.FoodList;
import com.example.calorie.entity.User;
import com.example.calorie.entity.UserProfile;

public interface RegistService {

	//ユーザ登録
	void registUser(User user);

	//UserProfileをインフラ層に
	UserProfileResponse registProfile(UserProfile userProfile);

	//FoodListをインフラ層に
	FoodListResponse insertFood(FoodList foodList);
}
