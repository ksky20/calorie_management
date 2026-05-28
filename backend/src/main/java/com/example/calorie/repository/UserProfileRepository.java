package com.example.calorie.repository;

import com.example.calorie.entity.UserProfile;

public interface UserProfileRepository {

	//userProfileの編集
	UserProfile editProfile(UserProfile userProfile);
}
