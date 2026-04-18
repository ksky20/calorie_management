package com.example.demo.form;

import java.math.BigDecimal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserProfileForm {

	@NotNull(message="選択してください")
	private String gender;
	
	@NotNull(message="入力してください")
	@Min(value=0, message="年齢を入力してください")
	@Max(value=150, message="年齢を入力してください")
	private Integer age;
	
	@NotNull(message="入力してください")
	@Min(value=1, message="	1以上の値を入力してください")
	@Max(value=300, message="300以下の値を入力してください")
	private BigDecimal height;
	
	@NotNull(message="入力してください")
	@Min(value=1, message="1以上の値を入力してください")
	@Max(value=300, message="300以下の値を入力してください")
	private BigDecimal weight;
	
	@NotNull(message="選択してください")
	@Min(value=1, message="1-5で指定してください。")
	@Max(value=5, message="1-5で指定してください。")
	private Integer activity;
	
}
