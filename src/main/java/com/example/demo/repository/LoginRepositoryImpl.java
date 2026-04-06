package com.example.demo.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Profile;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LoginRepositoryImpl implements LoginRepository {

	private final JdbcTemplate jdbcTemplate;
	
	@Override
	public Profile findByUserId(int userId) {
		
		String sql = "SELECT * FROM profile WHERE userId = ?";

		List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, userId);
		
		if(results.isEmpty()) {
			return null;
		}
		
		Map<String, Object> map = results.get(0);
		
		Profile profile = new Profile();
		profile.setGender((String)map.get("gender"));
		profile.setAge(Integer.parseInt(map.get("age").toString()));
		profile.setHeight((BigDecimal)map.get("height"));
		profile.setWeight((BigDecimal)map.get("weight"));
		profile.setActivity(Integer.parseInt(map.get("activity").toString()));
		profile.setIdeal_calories(Integer.parseInt(map.get("ideal_calories").toString()));
		profile.setUserId(Integer.parseInt(map.get("userId").toString()));
				
		return profile;
	}

}
