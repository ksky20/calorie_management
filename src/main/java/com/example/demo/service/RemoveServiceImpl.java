package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Food;
import com.example.demo.repository.RemoveRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RemoveServiceImpl implements RemoveService {

	private final RemoveRepository repository;
	
	//指定した項目を削除
	@Override
	public void removeFoods(Food food) {
		
		repository.removeFoods(food);
	}

}
