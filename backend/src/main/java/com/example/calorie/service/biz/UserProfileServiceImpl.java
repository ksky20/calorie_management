package com.example.calorie.service.biz;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.calorie.dto.response.UserProfileResponse;
import com.example.calorie.entity.UserProfile;
import com.example.calorie.repository.UserProfileRepository;
import com.example.calorie.service.calc.CalcService;
import com.example.calorie.service.common.ResponseCreator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

	private final UserProfileRepository userProfileRepository;
	private final ResponseCreator responseCreator;
	private final CalcService calcService;

	//userProfileの編集
	@Override
	public UserProfileResponse editProfile(UserProfile userProfile) {

		//idealCaloriesの計算、set
		int idealCalories = calcService.calcIdealCalories(userProfile);
		userProfile.setIdealCalories(idealCalories);

		UserProfile created = userProfileRepository.editProfile(userProfile);

		//Entity → Responseに変換
		UserProfileResponse result = responseCreator.createUserProfileResponse(created);

		return result;
	}



}
