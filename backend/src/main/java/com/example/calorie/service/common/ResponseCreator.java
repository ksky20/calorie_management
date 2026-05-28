package com.example.calorie.service.common;

import java.util.List;

import com.example.calorie.dto.response.FoodListResponse;
import com.example.calorie.dto.response.UserProfileResponse;
import com.example.calorie.entity.FoodList;
import com.example.calorie.entity.UserProfile;

public interface ResponseCreator {

	//UserProfile(Entity) → Response
	UserProfileResponse createUserProfileResponse(UserProfile e);
	List<UserProfileResponse> createUserProfileResponseList(List<UserProfile> l);

	//FoodList(Entity) → Response
	FoodListResponse createFoodListResponse(FoodList e);
	List<FoodListResponse> createFoodListResponseList(List<FoodList> l);
}
