package com.example.demo.form;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import lombok.Data;

@Data
public class PastDateForm {

	@NotNull(message="日付を入力してください")
	@Past(message="今日以前の日付を入力してください")
	private LocalDate date;
}
