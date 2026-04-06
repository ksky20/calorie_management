package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.repository.FoodRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

	private final FoodRepository repository;
	
//	今日食べたもの一覧表示
	@Override
	public List<Map<String, Object>> selectByUser_idAndDate(int user_id, LocalDate date) {
		
		return repository.selectByUser_idAndDate(user_id, date);
	}

	@Override
	public int getTotalCalories(int user_id, LocalDate date) {
		
		return repository.getTotalCalories(user_id, date);
	}

}
