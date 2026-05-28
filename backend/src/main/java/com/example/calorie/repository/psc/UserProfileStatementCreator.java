package com.example.calorie.repository.psc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.example.calorie.entity.UserProfile;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserProfileStatementCreator implements PreparedStatementCreator {

	private final UserProfile userProfile;

	@Override
	public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

		String sql = "INSERT INTO user_profile (user_id, age, gender, height, weight, activity, ideal_calories) " +
						 " VALUES (?, ?, ?, ?, ?, ?, ?)";

		//自動採番を取得
		PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		ps.setLong(1,  userProfile.getUserId());
		ps.setInt(2, userProfile.getAge());
		ps.setString(3, userProfile.getGender());
		ps.setBigDecimal(4, userProfile.getHeight());
		ps.setBigDecimal(5, userProfile.getWeight());
		ps.setInt(6, userProfile.getActivity());
		ps.setInt(7, userProfile.getIdealCalories());

		return ps;
	}

}
