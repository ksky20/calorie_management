package com.example.demo.repository;

import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

	private final JdbcTemplate jdbcTemplate;
	
	@Override
	public User selectByUsername(String username) {
		
		String sql = "SELECT " +
						  " id, " +
						  " username, " +
						  " password " +
						  "FROM " +
						  " users " +
						  "WHERE " +
						  " username = ?";
		
		User user;
		
		try {

			Map<String, Object> one 
				= jdbcTemplate.queryForMap(sql, username);

			user = new User();
			user.setUsername((String)one.get("username"));
			user.setPassword((String)one.get("password"));
			user.setId((int)one.get("id"));

		} catch (EmptyResultDataAccessException e) {
			user = null;
		}

		return user;
	}

}
