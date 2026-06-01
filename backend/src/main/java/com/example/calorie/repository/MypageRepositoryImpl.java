package com.example.calorie.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.calorie.entity.FoodList;
import com.example.calorie.entity.UserProfile;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MypageRepositoryImpl implements MypageRepository {

	private final JdbcTemplate jdbcTemplate;

	//userProfileが登録済みか判別
	@Override
	public boolean existById(Long id) {

		String sql = "SELECT COUNT(*) FROM user_profile WHERE user_id = ?";

		int count = jdbcTemplate.queryForObject(sql, Integer.class, id);

		return count > 0;
	}

	//userProfile一覧取得
	@Override
	public UserProfile selectProfile(Long userId) {

		String sql = "SELECT * " +
						 "FROM user_profile WHERE user_id = ?";

		Map<String, Object> result = jdbcTemplate.queryForMap(sql, userId);

		if(result.isEmpty()) {
			return null;
		}

		UserProfile userProfile = new UserProfile();
		userProfile.setUserId(Long.parseLong(result.get("user_id").toString()));
		userProfile.setAge(Integer.parseInt(result.get("age").toString()));
		userProfile.setGender((String)result.get("gender"));
		userProfile.setHeight((BigDecimal)result.get("height"));
		userProfile.setWeight((BigDecimal)result.get("weight"));
		userProfile.setActivity(Integer.parseInt(result.get("activity").toString()));
		userProfile.setIdealCalories(Integer.parseInt(result.get("ideal_calories").toString()));

		return userProfile;
	}

	//foodList一覧取得
	@Override
	public List<FoodList> selectByUserIdAndDate(Long userId, LocalDate date) {

		String sql = "SELECT * " +
						 "FROM food_list WHERE user_id = ? AND food_date = ?";

		// jdbcTemplate.queryForList() を利用して、Map型のListを取得
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, userId, date);

		// List<FoodList> ← List<<FoodList<>>
		List<FoodList> list = new ArrayList<>();
		for (Map<String, Object> row : rows) {
			list.add(createFoodList(row));
		}

		// List<Task> 型式で return
		return list;
	}

	//取得したMap → Entity
	private FoodList createFoodList(Map<String, Object> row) {

		FoodList foodList = new FoodList();
		foodList.setId((Long) row.get("id"));
		foodList.setUserId((Long) row.get("user_id"));
		// due_date : LocalDate ← java.sql.Date ← Object
		Object due = row.get("food_date");
		foodList.setFoodDate(((java.sql.Date) due).toLocalDate());
		foodList.setFoodName((String) row.get("food_name"));
		foodList.setFoodCalorie((Integer) row.get("food_calorie"));

		return foodList;
	}

	//1日の総摂取カロリー取得
	@Override
	public int sumTotalCalorie(Long userId, LocalDate date) {

		String sql = "SELECT SUM(food_calorie) " +
							"FROM food_list WHERE user_id = ? AND food_date = ?";

		Integer result = jdbcTemplate.queryForObject(sql, Integer.class, userId, date);
		return result != null ? result : 0;
	}

	//foodListから削除
	@Override
	public void deleteFood(Long id) {

		String sql = "DELETE FROM food_list WHERE id = ?";

		jdbcTemplate.update(sql, id);
	}

}
