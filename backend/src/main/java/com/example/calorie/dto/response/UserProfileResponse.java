package com.example.calorie.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponse {

	private Long id;
	private Long userId;
	private Integer age;
	private String gender;
	private BigDecimal height;
	private BigDecimal weight;
	private Integer activity;
	private Integer idealCalories;
}
