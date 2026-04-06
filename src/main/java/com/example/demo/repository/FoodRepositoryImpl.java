package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FoodRepositoryImpl implements FoodRepository {

	private final JdbcTemplate jdbcTemplate;
	
//	今日食べたもの一覧表示
	@Override
	public List<Map<String, Object>> selectByUser_idAndDate(int user_id, LocalDate date) {
		
		String sql = "SELECT food_name, food_calories " + 
						 	"FROM foods_list " + 
						 	"WHERE user_id = ? " + 
						 	"AND food_date = ?";
		
		return jdbcTemplate.queryForList(sql, user_id, date);
		
	}

	@Override
	public int getTotalCalories(int user_id, LocalDate date) {
		
		String sql = "SELECT SUM(food_calories) " +
						  "FROM foods_list " + 
						  "WHERE user_id = ? " +
						  "AND food_date = ? ";
		
		Integer total = jdbcTemplate.queryForObject(sql, Integer.class, user_id, date);
		return total != null ? total : 0; 
	}

}
