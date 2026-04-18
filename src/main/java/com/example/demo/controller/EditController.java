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
import com.example.demo.form.RegistFoodForm;
import com.example.demo.form.UserProfileForm;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.service.FoodService;
import com.example.demo.service.RegistService;
import com.example.demo.service.RemoveService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditController {

	private final RegistService service;
	private final RemoveService removeService;
	private final FoodService foodService;
	
//	プロフィール変更画面表示
	@PostMapping("/edit-profile")
	public String showEditProfile(@ModelAttribute UserProfileForm form, Model model) {

	    return "edit-profile";
	}
	
	// プロフィール変更完了
	@PostMapping("/edit-complete")
	public String editProfile(@Validated @ModelAttribute UserProfileForm form, BindingResult result, Model model) {
		
		UserDetailsImpl principal = (UserDetailsImpl)SecurityContextHolder.getContext()
																										   .getAuthentication()
																										   .getPrincipal();
		int userId = principal.getId();
		
		if(result.hasErrors()) {
			return "edit-profile";
		}
		
		Profile profile = new Profile();
		profile.setGender(form.getGender());
		profile.setHeight(form.getHeight());
		profile.setWeight(form.getWeight());
		profile.setAge(form.getAge());
		profile.setActivity(form.getActivity());
		profile.setUserId(userId);
				
				
//		DBに編集を保存
		service.editProfile(profile);
		
	    return "redirect:/";
	}
	
	//食べたもの編集画面
	@PostMapping("/edit-foods-list")
	public String editFoodsList(@ModelAttribute RegistFoodForm form) {
		
		return "edit-food";
	}
	
	//食べたもの編集完了
	@PostMapping("/edit-food-complete")
	public String editFoodsComplete(@Validated @ModelAttribute RegistFoodForm form, BindingResult result, Model model) {
		
		UserDetailsImpl principal = (UserDetailsImpl)SecurityContextHolder.getContext()
				   																						   .getAuthentication()
				   																						   .getPrincipal();
		int userId = principal.getId();
		LocalDate date = form.getFood_date();
		
		if(result.hasErrors()) {
			return "edit-food";
		}
		
		Food food = new Food();
		food.setFood_date(form.getFood_date());
		food.setId(form.getId());
		food.setFood_name(form.getFood_name());
		food.setFood_calories(form.getFood_calories());
		food.setUser_id(userId);
				
		service.editFood(food);
		
		// 検索した日付の食べたものリスト
		List<Map<String, Object>> searchFoodsList = foodService.selectByUser_idAndDate(userId, date);
		model.addAttribute("searchFoodsList", searchFoodsList);

		// その日の総カロリー
		int searchTotalCalories = foodService.getTotalCalories(userId, date);
		model.addAttribute("searchTotalCalories", searchTotalCalories);
		model.addAttribute("date", date);
				
		return "show-foods-list";
	}
	
	//削除画面へ
	@PostMapping("/remove-foods-list")
	public String removeFoodsConfirm(@Validated @ModelAttribute RegistFoodForm form, BindingResult result) {
		
		if (result.hasErrors()) {
			throw new IllegalArgumentException("**removeFoodsConfirm()**");
		}
		
		return "remove-foods-confirm";
	}
	
	//削除完了
	@PostMapping("/remove-foods-complete")
	public String removeFoodsComplete(@Validated @ModelAttribute RegistFoodForm form, BindingResult result) {
		
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			throw new IllegalArgumentException("**removeFoodsComplete()**");
		}
		
		Food food = new Food();
		food.setId(form.getId());
		food.setFood_date(form.getFood_date());
		food.setFood_name(form.getFood_name());
		food.setFood_calories(form.getFood_calories());
		
		removeService.removeFoods(food);
		
		return "remove-foods-complete";
	}
	
}
