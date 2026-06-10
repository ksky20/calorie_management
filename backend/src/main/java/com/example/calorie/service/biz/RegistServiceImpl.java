package com.example.calorie.service.biz;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.calorie.dto.response.FoodListResponse;
import com.example.calorie.dto.response.UserProfileResponse;
import com.example.calorie.entity.FoodList;
import com.example.calorie.entity.User;
import com.example.calorie.entity.UserProfile;
import com.example.calorie.repository.RegistRepository;
import com.example.calorie.service.calc.CalcService;
import com.example.calorie.service.common.ResponseCreator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RegistServiceImpl implements RegistService {

	private final RegistRepository registRepository;
	private final ResponseCreator responseCreator;
	private final PasswordEncoder passwordEncoder;
	private final CalcService calcService;

	//ユーザ登録
	@Override
	public void registUser(User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		registRepository.insertUser(user);
	}

	//プロフィール情報を登録
	@Override
	public boolean registProfile(UserProfile userProfile) {

		int idealCalories = calcService.calcIdealCalories(userProfile);

		userProfile.setIdealCalories(idealCalories);

		//インフラ層に渡す
		boolean result = registRepository.registUser(userProfile);

		//Entity → Responseに変換
//		UserProfileResponse result = responseCreator.createUserProfileResponse(created);

		return result;
	}

//	食べたものを追加
	@Override
	public boolean insertFood(FoodList foodList) {

		//インフラ層に
		boolean result = registRepository.insertFood(foodList);

		//Entity → Response
//		FoodListResponse result = responseCreator.createFoodListResponse(created);

		return result;
	}



}
