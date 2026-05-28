package com.example.calorie.service.calc;

import org.springframework.stereotype.Service;

import com.example.calorie.entity.UserProfile;

@Service
public class CalcServiceImpl implements CalcService {

	@Override
	public Integer calcIdealCalories(UserProfile userProfile) {

		String gender = userProfile.getGender();
		double height = (userProfile.getHeight()).doubleValue();
		double weight = (userProfile.getWeight()).doubleValue();
		int age = userProfile.getAge();
		int activity = userProfile.getActivity();

//		理想のカロリー計算
		int idealCalories = 0;
		if(gender.equals("male")) {

			double bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);

			switch(activity) {
				case 1:
					idealCalories = (int)(bmr * 1.2);
					break;
				case 2:
					idealCalories =  (int)(bmr * 1.375);
					break;
				case 3:
					idealCalories = (int)(bmr * 1.55);
					break;
				case 4:
					idealCalories = (int)(bmr * 1.725);
					break;
				case 5:
					idealCalories = (int)(bmr * 1.9);
					break;
			}
		} else {

			double bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);

			switch(activity) {
				case 1:
					idealCalories = (int)(bmr * 1.2);
					break;
				case 2:
					idealCalories =  (int)(bmr * 1.375);
					break;
				case 3:
					idealCalories = (int)(bmr * 1.55);
					break;
				case 4:
					idealCalories = (int)(bmr * 1.725);
					break;
				case 5:
					idealCalories = (int)(bmr * 1.9);
					break;
			}
		}

		return idealCalories;
	}

}
