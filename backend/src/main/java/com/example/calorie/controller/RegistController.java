package com.example.calorie.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.calorie.dto.request.FoodListRequest;
import com.example.calorie.dto.request.UserProfileRequest;
import com.example.calorie.dto.request.UserRequest;
import com.example.calorie.dto.response.FoodListResponse;
import com.example.calorie.dto.response.UserProfileResponse;
import com.example.calorie.entity.FoodList;
import com.example.calorie.entity.User;
import com.example.calorie.entity.UserProfile;
import com.example.calorie.security.UserDetailsImpl;
import com.example.calorie.service.biz.RegistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RegistController {

	private final RegistService registService;

	//ユーザ情報登録
	@PostMapping("/regist")
	public ResponseEntity<?> registUser(@Validated @RequestBody UserRequest req) {

		User user = new User();
		user.setUsername(req.getUsername());
		user.setPassword(req.getPassword());

		registService.registUser(user);

		return ResponseEntity.ok().build();
	}

	//プロフィール登録
	@PostMapping("/regist-profile")
	public ResponseEntity<UserProfileResponse> registProfile(
			@Validated @RequestBody UserProfileRequest req,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {

		//サービス層に渡して登録処理
		UserProfile userProfile = new UserProfile();
		userProfile.setAge(req.getAge());
		userProfile.setGender(req.getGender());
		userProfile.setHeight(req.getHeight());
		userProfile.setWeight(req.getWeight());
		userProfile.setActivity(req.getActivity());
		userProfile.setUserId(userDetails.getId());

		UserProfileResponse created = registService.registProfile(userProfile);

		//ResponseのLocation設定のためのURI作成
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
																	.path("{id}")
																	.buildAndExpand(created.getId())
																	.toUri();

		return ResponseEntity.created(location)
									   .body(created);
	}

	//食べたもの登録
	@PostMapping("/add-food")
	public ResponseEntity<FoodListResponse> insertFood(
			@Validated @RequestBody FoodListRequest req,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {

		//サービス層へ
		FoodList foodList = new FoodList();
		foodList.setFoodDate(req.getFoodDate());
		foodList.setFoodName(req.getFoodName());
		foodList.setFoodCalorie(req.getFoodCalorie());
		foodList.setUserId(userDetails.getId());

		FoodListResponse created = registService.insertFood(foodList);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
																	.path("{id}")
																	.buildAndExpand(created.getId())
																	.toUri();

		return ResponseEntity.created(location)
										.body(created);
	}


}
