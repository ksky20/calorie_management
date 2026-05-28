package com.example.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Food;
import com.example.demo.entity.Profile;
import com.example.demo.entity.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RegistRepositoryImpl implements RegistRepository {

	private final JdbcTemplate jdbcTemplate;
	
//	アプリの登録
	@Override
	public void addUserInfo(User user) {
		
		String sql = 	" INSERT INTO users" +
							" (username, password) " +
							" VALUES (?, ?) ";	

			jdbcTemplate.update(sql, user.getUsername(),
									 user.getPassword());
	}

//	プロフィールの登録
	@Override
	public void addProfile(Profile profile) {
		
		String sql = 	"INSERT INTO profile " + 
							"(user_id, gender, height, weight, age, activity, ideal_calories) " + 
							"VALUES(?, ?, ? ,?, ?, ?, ?)";
		
		jdbcTemplate.update(sql, profile.getUserId(), profile.getGender(), profile.getHeight(), profile.getWeight(), 
												profile.getAge(), profile.getActivity(), profile.getIdeal_calories());
	}

//	食べたものとカロリーの追加
	@Override
	public void addFood(Food food) {
		
		System.out.println("repository : " + food.getUser_id());
		
		String sql = 	"INSERT INTO foods_list " + 
							"(user_id, food_date, food_name, food_calories) " + 
							"VALUES(?, ?, ?, ?)";
		
		jdbcTemplate.update(sql, food.getUser_id(), food.getFood_date(), food.getFood_name(), food.getFood_calories());
	}

//	プロフィールの変更
	@Override
	public void editProfile(Profile profile) {
		
		String sql =  "UPDATE profile " +
		                   "SET gender = ?, height = ?, weight = ?, age = ?, activity = ? " +
		                   "WHERE user_id = ?";

		    jdbcTemplate.update(sql,  profile.getGender(),
		    									   profile.getHeight(),
		    									   profile.getWeight(),
		    									   profile.getAge(),
		    									   profile.getActivity(),
		    									   profile.getUserId());
	}

	@Override
	public void editFood(Food food) {
		
		String sql = "UPDATE foods_list " +
						  "SET food_date = ?, food_name = ?, food_calories = ? " +
						  "WHERE id = ?";
		
		jdbcTemplate.update(sql, food.getFood_date(),
											  food.getFood_name(),
											  food.getFood_calories(),
											  food.getId());
	}
	
	

	

}
