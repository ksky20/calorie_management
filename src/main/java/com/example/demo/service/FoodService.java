package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface FoodService {

	List<Map<String, Object>> selectByUser_idAndDate(int user_id, LocalDate date);
	
	int getTotalCalories(int user_id, LocalDate date);
}
