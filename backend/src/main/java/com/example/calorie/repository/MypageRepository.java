package com.example.calorie.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.example.calorie.entity.FoodList;
import com.example.calorie.entity.UserProfile;

public interface MypageRepository {

	//userProfile登録済みか判別
	boolean existById(Long id);

	//userProfile取得
	UserProfile selectProfile(Long userId);

	//foodList一覧表示
	List<FoodList> selectByUserIdAndDate(Long userId, LocalDate today);

	//1日の総摂取カロリー
	int sumTotalCalorie(Long userId, LocalDate date);

	//foodListから削除
	void deleteFood(Long id);

}
