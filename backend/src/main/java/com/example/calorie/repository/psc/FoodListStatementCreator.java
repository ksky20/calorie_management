package com.example.calorie.repository.psc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.example.calorie.entity.FoodList;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FoodListStatementCreator implements PreparedStatementCreator {

	private final FoodList foodList;

	@Override
	public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

		String sql = "INSERT INTO food_list (user_id, food_date, food_name, food_calorie) " +
				 " VALUES (?, ?, ?, ?)";

		//自動採番を取得
		PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		ps.setLong(1, foodList.getUserId());
		ps.setDate(2, java.sql.Date.valueOf(foodList.getFoodDate()));
		ps.setString(3, foodList.getFoodName());
		ps.setInt(4, foodList.getFoodCalorie());

		return ps;
	}

}
