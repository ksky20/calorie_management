package com.example.demo.form;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import lombok.Data;

@Data
public class RegistFoodForm {
	
	@Past(message="今日以前の日付を入力してください")
	private LocalDate food_date;
	
	@NotNull(message="入力してください")
	private String food_name;
	
	@NotNull(message="入力してください")
	private Integer food_calories;
}
