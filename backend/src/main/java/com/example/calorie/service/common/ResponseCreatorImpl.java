package com.example.calorie.service.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.calorie.dto.response.FoodListResponse;
import com.example.calorie.dto.response.UserProfileResponse;
import com.example.calorie.entity.FoodList;
import com.example.calorie.entity.UserProfile;

@Service
public class ResponseCreatorImpl implements ResponseCreator {

	//UserProfile → Response作成
	@Override
	public UserProfileResponse createUserProfileResponse(UserProfile e) {

		UserProfileResponse result = new UserProfileResponse(e.getId(),
																					e.getUserId(),
																					e.getAge(),
																					e.getGender(),
																					e.getHeight(),
																					e.getWeight(),
																					e.getActivity(),
																					e.getIdealCalories()
																					);

		return result;
	}

	//List<UserProfile> → List<UserProfileResponse>作成
	@Override
	public List<UserProfileResponse> createUserProfileResponseList(List<UserProfile> l) {

		List<UserProfileResponse> result = new ArrayList<>();

		for(UserProfile u: l) {
			result.add(createUserProfileResponse(u));
		}

		return result;
	}

	// FoodList(Entity) → FoodListResponse
	@Override
	public FoodListResponse createFoodListResponse(FoodList e) {

		FoodListResponse result = new FoodListResponse(e.getId(),
																			  e.getUserId(),
																			  e.getFoodDate(),
																			  e.getFoodName(),
																			  e.getFoodCalorie()
																			  );

		return result;
	}

	//List<FoodList> → List<FoodListResponse>作成
	@Override
	public List<FoodListResponse> createFoodListResponseList(List<FoodList> l) {

		//List<FoodListResponse>を作って
		List<FoodListResponse> result = new ArrayList<>();

		//Entity → Responseにして、Listに入れる
		for(FoodList u: l) {
			result.add(createFoodListResponse(u));
		}

		return result;
	}

}
