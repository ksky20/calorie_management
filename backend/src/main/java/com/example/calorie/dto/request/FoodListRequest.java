package com.example.calorie.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodListRequest {

	@NotNull
	private LocalDate foodDate;

	@NotNull
	@Size(max = 20)
	private String foodName;

	@NotNull
	@Min(1)
	@Max(10000)
	private Integer foodCalorie;
}
