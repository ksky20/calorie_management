package com.example.demo.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

	private String gender;
	private Integer age;
	private BigDecimal height;
	private BigDecimal weight;
	private Integer activity;
	private Integer ideal_calories;
	private Integer userId;
	
}
