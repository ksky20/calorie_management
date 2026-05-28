package com.example.calorie.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.calorie.entity.UserProfile;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserProfileRepositoryJdbc implements UserProfileRepository {

	private final JdbcTemplate jdbcTemplate;

	//userProfileの編集
	@Override
	public UserProfile editProfile(UserProfile userProfile) {

		String sql = "UPDATE user_profile "+
							"SET age = ?, gender= ?, height = ?, weight = ?, activity = ? , ideal_calories = ? " +
							"WHERE user_id = ?";

		jdbcTemplate.update(sql,
				userProfile.getAge(),
				userProfile.getGender(),
				userProfile.getHeight(),
				userProfile.getWeight(),
				userProfile.getActivity(),
				userProfile.getIdealCalories(),
				userProfile.getUserId()
				);

		return new UserProfile(
				userProfile.getId(),
				userProfile.getUserId(),
				userProfile.getAge(),
				userProfile.getGender(),
				userProfile.getHeight(),
				userProfile.getWeight(),
				userProfile.getActivity(),
				userProfile.getIdealCalories()
				);

	}

}
