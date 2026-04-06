package com.example.demo.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class UserRegistForm {

	@NotNull(message="入力してください")
	@Size(min = 1, max = 16, message = "1~16文字で入力してください")
	private String username;
	
	@NotNull(message="入力してください")
	@Size(min = 1, message = "入力してください")
	private String password;
}
