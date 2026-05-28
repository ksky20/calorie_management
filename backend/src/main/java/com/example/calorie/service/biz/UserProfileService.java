package com.example.calorie.service.biz;

import com.example.calorie.dto.response.UserProfileResponse;
import com.example.calorie.entity.UserProfile;

public interface UserProfileService {

	//userProfileの編集
	UserProfileResponse editProfile(UserProfile userProfile);
}
