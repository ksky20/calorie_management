package com.example.calorie.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileRequest {

	@NotNull
	@Min(0)
	@Max(150)
	private Integer age;

	@NotNull
	private String gender;

	@NotNull
	@Min(1)
	@Max(300)
	private BigDecimal height;

	@NotNull
	@Min(1)
	@Max(300)
	private BigDecimal weight;

	@NotNull
	private Integer activity;
}
