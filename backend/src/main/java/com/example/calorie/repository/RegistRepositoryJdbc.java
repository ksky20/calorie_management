package com.example.calorie.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.calorie.entity.FoodList;
import com.example.calorie.entity.User;
import com.example.calorie.entity.UserProfile;
import com.example.calorie.repository.psc.FoodListStatementCreator;
import com.example.calorie.repository.psc.UserProfileStatementCreator;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RegistRepositoryJdbc implements RegistRepository {

	private final JdbcTemplate jdbcTemplate;

	//ユーザ情報を登録
	@Override
	public void insertUser(User user) {

		String sql = "INSERT users (username, password) " +
							"VALUES(?, ?)";

		jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
	}

	//UserProfileをDBに登録
	@Override
	public boolean registUser(UserProfile userProfile) {

		//自動採番したIDを取得する
		//必要ないのでは？
//		PreparedStatementCreator psc = new UserProfileStatementCreator(userProfile);
//		KeyHolder kh = new GeneratedKeyHolder();
//
//		jdbcTemplate.update(psc, kh);
//
//		Long newId = kh.getKey().longValue();

//		UserProfile created = new UserProfile(newId,
//																userProfile.getUserId(),
//																userProfile.getAge(),
//																userProfile.getGender(),
//																userProfile.getHeight(),
//																userProfile.getWeight(),
//																userProfile.getActivity(),
//																userProfile.getIdealCalories()
//															   );

		String sql = "INSERT INTO user_profile (user_id, age, gender, height, weight, activity, ideal_calories) " +
	 						" VALUES (?, ?, ?, ?, ?, ?, ?)";

		int rows = jdbcTemplate.update(sql,
											userProfile.getUserId(),
											userProfile.getAge(),
											userProfile.getGender(),
											userProfile.getHeight(),
											userProfile.getWeight(),
											userProfile.getActivity(),
											userProfile.getIdealCalories());

		return rows > 0;
	}

	//FoodListに追加
	@Override
	public boolean insertFood(FoodList foodList) {

		//自動採番したIDを取得する
//		PreparedStatementCreator psc = new FoodListStatementCreator(foodList);
//		KeyHolder kh = new GeneratedKeyHolder();
//
//		jdbcTemplate.update(psc, kh);
//
//		Long newId = kh.getKey().longValue();

//		FoodList created = new FoodList(newId,
//														foodList.getUserId(),
//														foodList.getFoodDate(),
//														foodList.getFoodName(),
//														foodList.getFoodCalorie()
//													  );

		String sql = "INSERT INTO food_list (user_id, food_date, food_name, food_calorie) " +
							" VALUES (?, ?, ?, ?)";

		int rows = jdbcTemplate.update(sql,
											foodList.getUserId(),
											foodList.getFoodDate(),
											foodList.getFoodName(),
											foodList.getFoodCalorie());

		return rows > 0;
	}


}
