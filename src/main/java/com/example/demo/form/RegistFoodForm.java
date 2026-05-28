package com.example.demo.form;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class RegistFoodForm {
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate food_date;
	
	private Integer id;
	
	@NotNull(message="入力してください")
	@Size(max = 20, message = "20文字以内で入力してください")
	private String food_name;
	
	@NotNull(message="入力してください")
	@Min(value = 0, message = "0以上の値を入力してください")
	@Max(value = 10000, message = "10000以下の値を入力してください")
	private Integer food_calories;
}
