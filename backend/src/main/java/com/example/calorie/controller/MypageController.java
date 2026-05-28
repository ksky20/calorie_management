package com.example.calorie.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.calorie.dto.response.FoodListResponse;
import com.example.calorie.dto.response.UserProfileResponse;
import com.example.calorie.security.UserDetailsImpl;
import com.example.calorie.service.biz.MypageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MypageController {

	private final MypageService mypageService;

	//userProfileの有無確認
	@GetMapping("/profile-check")
	public ResponseEntity<?> checkProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {

		Long id = userDetails.getId();
		boolean isProfile = mypageService.existById(id);

		return ResponseEntity.ok(isProfile);
	}

	//userProfileの情報取得
	@GetMapping("/show-user-profile")
	public ResponseEntity<UserProfileResponse> getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {

		Long userId = userDetails.getId();

		UserProfileResponse body = mypageService.findProfileById(userId);

		return ResponseEntity.ok(body);
	}

	//食べたものの全件表示
	@GetMapping("/show-food-list")
	public ResponseEntity<List<FoodListResponse>> getFoodListAll(@AuthenticationPrincipal UserDetailsImpl userDetails) {

		Long userId = userDetails.getId();
		LocalDate today = LocalDate.now();

		List<FoodListResponse> body = mypageService.findAll(userId, today);

		return ResponseEntity.ok(body);
	}

	//foodListから削除
	@DeleteMapping("/delete-food/{id}")
	public ResponseEntity<?> deleteFood(@PathVariable Long id) {

		mypageService.deleteFood(id);

		return ResponseEntity.ok().build();
	}
}
