package com.example.calorie.service.calc;

import com.example.calorie.entity.UserProfile;

public interface CalcService {

	//idealCaloriesの計算
	public Integer calcIdealCalories(UserProfile userProfile);
}
