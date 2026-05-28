package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface FoodRepository {

	List<Map<String, Object>> selectByUser_idAndDate(int user_id, LocalDate date);
	
	int getTotalCalories(int user_id, LocalDate date);
}
