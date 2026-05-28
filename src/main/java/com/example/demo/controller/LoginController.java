package com.example.demo.controller;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Profile;
import com.example.demo.form.PastDateForm;
import com.example.demo.form.RegistFoodForm;
import com.example.demo.form.UserProfileForm;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.service.FoodService;
import com.example.demo.service.LoginService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;
	private final FoodService foodService;
	
//	マイページ表示
	@GetMapping("/")
	public String showMyPage(Model model) {
				
		UserDetailsImpl principal = (UserDetailsImpl)SecurityContextHolder.getContext()
																											 .getAuthentication()
																											 .getPrincipal();
		int userId = principal.getId();		
		LocalDate today = LocalDate.now();
		
//		プロフィール情報取得
		Profile profile = loginService.findUserByUserId(userId);
		model.addAttribute("profile", profile);
		
//		食べたものの情報取得
		List<Map<String, Object>> foods_list = foodService.selectByUser_idAndDate(userId, today);		
		model.addAttribute("foods_list", foods_list);
		
//		1日の総摂取カロリー
		int totalCalories = foodService.getTotalCalories(userId, today);
		model.addAttribute("totalCalories", totalCalories);
		
//		日付検索
		if(!model.containsAttribute("searchFoodsList")){
			model.addAttribute("searchFoodsList", null);
		}

		if(!model.containsAttribute("searchTotalCalories")){
			model.addAttribute("searchTotalCalories", null);
		}		 
		 
//		Formクラスの生成
		model.addAttribute("userProfileForm", new UserProfileForm());
		model.addAttribute("pastDateForm", new PastDateForm());
		model.addAttribute("registFoodForm", new RegistFoodForm());
				
		return "mypage";
	}
	
}
