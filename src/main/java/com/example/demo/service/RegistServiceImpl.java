package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Food;
import com.example.demo.entity.Profile;
import com.example.demo.entity.User;
import com.example.demo.repository.RegistRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistServiceImpl implements RegistService {

	private final RegistRepository repository;
	private final PasswordEncoder passwordEncoder;
	
//	アプリ登録情報の受け渡し
	@Override
	public void registUser(User user) {
		
		String hashd = passwordEncoder.encode(user.getPassword());
		
		user.setPassword(hashd);
		
		repository.addUserInfo(user);
	}

//	プロフィール情報の受け渡し
	@Override
	public void registProfile(Profile profile) {
		
		String gender = profile.getGender();
		double height = (profile.getHeight()).doubleValue();
		double weight = (profile.getWeight()).doubleValue();
		int age = profile.getAge();
		int activity = profile.getActivity();
		
//		理想のカロリー計算
		int ideal_calories = 0;
		
		if(gender.equals("male")) {
			
			double bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
			
			switch(activity) {
				case 1:
					ideal_calories = (int)(bmr * 1.2);
					break;
				case 2:
					ideal_calories =  (int)(bmr * 1.375);
					break;
				case 3:
					ideal_calories = (int)(bmr * 1.55);
					break;
				case 4:
					ideal_calories = (int)(bmr * 1.725);
					break;
				case 5:
					ideal_calories = (int)(bmr * 1.9);
					break;
			}
		}else {
			
			double bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
			
			switch(activity) {
				case 1:
					ideal_calories = (int)(bmr * 1.2);
					break;
				case 2:
					ideal_calories =  (int)(bmr * 1.375);
					break;
				case 3:
					ideal_calories = (int)(bmr * 1.55);
					break;
				case 4:
					ideal_calories = (int)(bmr * 1.725);
					break;
				case 5:
					ideal_calories = (int)(bmr * 1.9);
					break;
			}
		}
		
		profile.setIdeal_calories(ideal_calories);
		
		repository.addProfile(profile);
	}

//	食べたものとカロリーの登録
	@Override
	public void registFood(Food food) {
		
		repository.addFood(food);
	}

//	プロフィール情報の変更
	@Override
	public void editProfile(Profile profile) {
		
		repository.editProfile(profile);
	}

}
