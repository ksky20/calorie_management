package com.example.calorie.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.calorie.entity.FoodList;
import com.example.calorie.entity.UserProfile;

public interface MypageRepository {

	//userProfile登録済みか判別
	boolean existById(Long id);

	//userProfile取得
	UserProfile getProfile(Long userId);

	//foodList一覧表示
	List<FoodList> selectAll(Long userId, LocalDate today);

	//foodListから削除
	void deleteFood(Long id);
}
