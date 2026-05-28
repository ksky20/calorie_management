package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class UserRegistForm {

	@NotBlank(message="入力してください")
	@Size(min = 4, max = 16, message = "1~16文字で入力してください")
	private String username;
	
	@NotBlank(message="入力してください")
	@Size(min = 4, max = 20, message = "4~20文字で入力してください")
	private String password;
}
