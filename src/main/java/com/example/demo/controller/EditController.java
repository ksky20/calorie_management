package com.example.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Profile;
import com.example.demo.form.UserProfileForm;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.service.RegistService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditController {

	private final RegistService service;
	
//	プロフィール変更画面表示
	@PostMapping("/edit-profile")
	public String showEditProfile(@ModelAttribute UserProfileForm form, Model model) {
		
		UserDetailsImpl principal = (UserDetailsImpl)SecurityContextHolder.getContext()
				 																						   .getAuthentication()
				 																						   .getPrincipal();
		int userId = principal.getId();
		
		Profile profile = new Profile();
		profile.setGender(form.getGender());
		profile.setHeight(form.getHeight());
		profile.setWeight(form.getWeight());
		profile.setAge(form.getAge());
		profile.setActivity(form.getActivity());
		profile.setUserId(userId);
		
		model.addAttribute("profile", profile);
		
	    return "edit-profile";
	}
	
	// 変更処理
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
				
		model.addAttribute("profile", profile);
				
//		DBに編集を保存
		service.editProfile(profile);
		
	    return "redirect:/";
	}
}
