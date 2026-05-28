package com.example.calorie.repository;

import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.calorie.entity.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

	private final JdbcTemplate jdbcTemplate;

	//ログイン時にusernameを渡してユーザ情報を取得
	@Override
	public User selectByUsername(String username) {

		String sql = "SELECT " +
							"id, " +
							"username, " +
							"password " +
							"FROM " +
							"users " +
							"WHERE username = ?";

		User user;

		try {

			Map<String, Object> one = jdbcTemplate.queryForMap(sql, username);

			user = new User();
			user.setId((Long) one.get("id"));
			user.setUsername((String) one.get("username"));
			user.setPassword((String) one.get("password"));

		}catch(EmptyResultDataAccessException e) {

			user = null;
		}

		//Entityで返す
		return user;
	}


}
