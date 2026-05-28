package com.example.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Food;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RemoveRepositoryImpl implements RemoveRepository {

	private final JdbcTemplate jdbcTemplate;
	
	//指定した項目を削除
	@Override
	public void removeFoods(Food food) {
		
		String sql = " DELETE " + 
						  " FROM foods_list " + 
						  " WHERE id = ? "; 
		
		jdbcTemplate.update(sql, food.getId());
	}

}
