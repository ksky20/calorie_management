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
	List<FoodListResponse> findByUserIdAndDate(Long userId, LocalDate today);

	//1日の総摂取カロリー取得
//	int getTotalCalorie(Long userId, LocalDate date);

	//foodListから削除
	void deleteFood(Long id);

	//日付検索
//	List<FoodListResponse> getFoodListByDate(Long userId, LocalDate date);
}
