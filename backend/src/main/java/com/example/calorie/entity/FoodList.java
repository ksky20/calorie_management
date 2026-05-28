package com.example.calorie.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodList {

	private Long id;
	private Long userId;
	private LocalDate foodDate;
	private String foodName;
	private Integer foodCalorie;
}
