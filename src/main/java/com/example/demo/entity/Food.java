package com.example.demo.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {

	private Integer id;
	private Integer user_id;
	private LocalDate food_date;
	private String food_name;
	private Integer food_calories;
}
