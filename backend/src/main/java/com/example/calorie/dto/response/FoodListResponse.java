package com.example.calorie.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodListResponse {

	private Long id;
	private Long userId;
	private LocalDate foodDate;
	private String foodName;
	private Integer foodCalorie;
}
