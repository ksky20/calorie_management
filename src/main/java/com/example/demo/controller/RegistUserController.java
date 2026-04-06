package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.User;
import com.example.demo.form.UserRegistForm;
import com.example.demo.service.RegistService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistUserController {

	private final RegistService service;
	
//	登録画面表示
	@GetMapping("/regist")
	public String registForm(Model model) {
		
		model.addAttribute("userRegistForm", new UserRegistForm());
		
		return "regist-user";
	}
	
//	ユーザ登録処理
	@PostMapping("/regist-form")
	public String registUser(@Validated @ModelAttribute UserRegistForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			
			return "regist-user";
		}
		
		User user = new User();
		user.setUsername(form.getUsername());
		user.setPassword(form.getPassword());
		
		service.registUser(user);
		
		return "redirect:/complete";
	}
	
//	登録完了確認画面
	@GetMapping("/complete")
	public String showRegistComplete() {
		
		return "regist-user-complete";
	}
}
