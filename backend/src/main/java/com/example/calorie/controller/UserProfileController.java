package com.example.calorie.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.calorie.dto.request.UserProfileRequest;
import com.example.calorie.dto.response.UserProfileResponse;
import com.example.calorie.entity.UserProfile;
import com.example.calorie.security.UserDetailsImpl;
import com.example.calorie.service.biz.UserProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserProfileController {

	private final UserProfileService userProfileService;

	//userProfileの編集
	@PutMapping("/edit-profile")
	public ResponseEntity<UserProfileResponse> editProfile(
			@Validated @RequestBody UserProfileRequest req,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {

		UserProfile userProfile = new UserProfile();
		userProfile.setAge(req.getAge());
		userProfile.setGender(req.getGender());
		userProfile.setHeight(req.getHeight());
		userProfile.setWeight(req.getWeight());
		userProfile.setActivity(req.getActivity());
		userProfile.setUserId(userDetails.getId());

		//update + Responseを取得
		UserProfileResponse created = userProfileService.editProfile(userProfile);

		//ResponseのLocation設定のためのURI作成
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
																	.path("{id}")
																	.buildAndExpand(created.getId())
																	.toUri();

		return ResponseEntity.created(location)
										.body(created);
	}
}
