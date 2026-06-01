package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface FoodRepository {

//	食べたもの一覧表示
	List<Map<String, Object>> selectByUser_idAndDate(int user_id, LocalDate date);

//	総摂取カロリー
	int sumTotalCalories(int user_id, LocalDate date);
}
