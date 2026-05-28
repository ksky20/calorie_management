package com.example.calorie.service.biz;

import java.time.LocalDate;
import java.util.List;

import com.example.calorie.dto.response.FoodListResponse;
import com.example.calorie.dto.response.UserProfileResponse;

public interface MypageService {

	//userProfileの有無確認
	boolean existById(Long id);

	//userProfileの情報を取得
	UserProfileResponse findProfileById(Long userId);

	//foodListの一覧取得
	List<FoodListResponse> findAll(Long userId, LocalDate today);

	//foodListから削除
	void deleteFood(Long id);
}
