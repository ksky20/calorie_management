package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Food;
import com.example.demo.entity.Profile;
import com.example.demo.form.PastDateForm;
import com.example.demo.form.RegistFoodForm;
import com.example.demo.form.UserProfileForm;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.service.FoodService;
import com.example.demo.service.LoginService;
import com.example.demo.service.RegistService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MypageController {

	private final RegistService registService;
	private final LoginService loginService;
	private final FoodService foodService;
	
//	プロフィール登録
	@PostMapping("/regist-profile")
	public String registProfile(@Validated @ModelAttribute UserProfileForm form, BindingResult result, Model model) {
			
		UserDetailsImpl principal = (UserDetailsImpl)SecurityContextHolder.getContext()
                                                                                                           .getAuthentication()
                                                                                                           .getPrincipal();
       
		int userId = principal.getId();	
	    LocalDate today = LocalDate.now();
		
//		Validationエラー時
		if(result.hasErrors()) {
			
//		    htmlに必要な情報
//			食べたものの情報取得
			List<Map<String, Object>> foods_list = foodService.selectByUser_idAndDate(userId, today);		
			model.addAttribute("foods_list", foods_list);
			
//			1日の総摂取カロリー
			int totalCalories = foodService.getTotalCalories(userId, today);
			model.addAttribute("totalCalories", totalCalories);
			
//			Formクラスの生成
			model.addAttribute("registFoodForm", new RegistFoodForm());
			model.addAttribute("pastDateForm", new PastDateForm());
			
			return "mypage";
		}
	    
//		マイページに必要な情報		
		Profile profile = new Profile();
		profile.setGender(form.getGender());
		profile.setHeight(form.getHeight());
		profile.setWeight(form.getWeight());
		profile.setAge(form.getAge());
		profile.setActivity(form.getActivity());
		profile.setUserId(userId);
				
//		DBに登録
		registService.registProfile(profile);
				
		return "redirect:/";
		
	}
	
//	食べたものとカロリーの登録
	@PostMapping("/regist-food")
	public String registFood(@Validated @ModelAttribute RegistFoodForm form, BindingResult result, Model model) {
		
		UserDetailsImpl principal = (UserDetailsImpl)SecurityContextHolder.getContext()
                                                                                                           .getAuthentication()
                                                                                                           .getPrincipal();
		
		int userId = principal.getId();	
	    LocalDate today = LocalDate.now();
	    

		System.out.println("controller : " + userId);
		System.out.println("controller : " + today);
		
//	    Validationエラー時
		if(result.hasErrors()) {
					
//			マイページに必要な情報
			Profile profile = loginService.findUserByUserId(userId);
			model.addAttribute("profile", profile);
			
//			食べたものの情報取得
			List<Map<String, Object>> foods_list = foodService.selectByUser_idAndDate(userId, today);		
			model.addAttribute("foods_list", foods_list);
			
//			1日の総摂取カロリー
			int totalCalories = foodService.getTotalCalories(userId, today);
			model.addAttribute("totalCalories", totalCalories);
			
//			Formクラスの生成
			model.addAttribute("userProfileForm", new UserProfileForm());
			model.addAttribute("pastDateForm", new PastDateForm());
			
			return "mypage";
		}
		
		Food food = new Food();
		food.setFood_date(today);
		food.setFood_name(form.getFood_name());
		food.setFood_calories(form.getFood_calories());
		food.setUser_id(userId);
				
		System.out.println("food : " + food);		
//		食べたものの登録
		registService.registFood(food);
						
		return "redirect:/";
	}
}
