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
public class SearchPastListController {

	private final LoginService loginService;
	private final FoodService foodService;
	
//	指定した日付の食べたものリストを表示
	@PostMapping("/search-food")
	public String searchFood(@Validated @ModelAttribute PastDateForm form, BindingResult result, Model model) {
				
		UserDetailsImpl principal = (UserDetailsImpl)SecurityContextHolder.getContext()
																											.getAuthentication()
																											.getPrincipal();
				
//		ユーザIDと指定した日付の取得
		int userId = principal.getId();
		LocalDate date = form.getDate();
		
//		今日の日付
		LocalDate today = LocalDate.now();

//		Validationエラー時
		if(result.hasErrors()) {
						
//			マイページに必要な情報
			Profile profile = loginService.findUserByUserId(userId);
			model.addAttribute("profile", profile);
			
//			食べたものリストの表示
//			List<Map<String, Object>> foods_list = foodService.selectByUser_idAndDate(userId, today);
//			model.addAttribute("foods_list", foods_list);
			
//			一日の合計摂取カロリー
		    int totalCalories = foodService.getTotalCalories(userId, today); 
		    model.addAttribute("totalCalories", totalCalories);
		    
//		    Formクラスの生成
		    model.addAttribute("userProfileForm", new UserProfileForm());
		    model.addAttribute("registFoodForm", new RegistFoodForm());
						
			return "mypage";
		}
		
//		検索した日付の食べたものリストの取得
		List<Map<String, Object>> searchFoodsList = foodService.selectByUser_idAndDate(userId, date);
		model.addAttribute("searchFoodsList", searchFoodsList);
		
//		その日の総カロリー
		int searchTotalCalories = foodService.getTotalCalories(userId, date);	
		model.addAttribute("searchTotalCalories", searchTotalCalories);
		
		model.addAttribute("date", date);
	    		
		return "show-foods-list";
	}
	
	//戻る
	@PostMapping("/search-food-rev")
	public String searchFoodsRev(@ModelAttribute RegistFoodForm form, Model model) {
		
		UserDetailsImpl principal = (UserDetailsImpl)SecurityContextHolder.getContext()
                																							.getAuthentication()
                																							.getPrincipal();
		int userId = principal.getId();
		LocalDate date = form.getFood_date(); // フォームから日付を取得

		// 検索した日付の食べたものリスト
		List<Map<String, Object>> searchFoodsList = foodService.selectByUser_idAndDate(userId, date);
		model.addAttribute("searchFoodsList", searchFoodsList);

		// その日の総カロリー
		int searchTotalCalories = foodService.getTotalCalories(userId, date);
		model.addAttribute("searchTotalCalories", searchTotalCalories);

		model.addAttribute("date", date);
		
		return "show-foods-list";
	}
	
}
