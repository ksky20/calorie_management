package com.example.calorie.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

	@NotBlank
	@Size(min = 4, max = 20)
	private String username;

	@NotBlank
	@Size(min = 4, max = 20)
	private String password;
}
