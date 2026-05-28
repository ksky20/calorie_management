package com.example.calorie.service.biz;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.calorie.dto.response.FoodListResponse;
import com.example.calorie.dto.response.UserProfileResponse;
import com.example.calorie.entity.FoodList;
import com.example.calorie.entity.UserProfile;
import com.example.calorie.repository.MypageRepository;
import com.example.calorie.service.common.ResponseCreator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {

	private final MypageRepository mypageRepository;
	private final ResponseCreator responseCreator;

	//userProfileの有無確認
	@Override
	@Transactional
	public boolean existById(Long id) {

		return mypageRepository.existById(id);
	}

	//userProfileの情報取得
	@Override
	@Transactional
	public UserProfileResponse findProfileById(Long userId) {

		UserProfile userProfile = mypageRepository.getProfile(userId);

		UserProfileResponse result = new UserProfileResponse();
		result.setUserId(userProfile.getUserId());
		result.setAge(userProfile.getAge());
		result.setGender(userProfile.getGender());
		result.setHeight(userProfile.getHeight());
		result.setWeight(userProfile.getWeight());;
		result.setActivity(userProfile.getActivity());
		result.setIdealCalories(userProfile.getIdealCalories());

		return result;
	}


	//foodList一覧取得
	@Override
	@Transactional(readOnly = true)
	public List<FoodListResponse> findAll(Long userId, LocalDate today) {

		List<FoodList> foodList = mypageRepository.selectAll(userId, today);

		List<FoodListResponse> result = responseCreator.createFoodListResponseList(foodList);

		return result;
	}

	//foodListから削除
	@Override
	@Transactional
	public void deleteFood(Long id) {

		mypageRepository.deleteFood(id);
	}




}
