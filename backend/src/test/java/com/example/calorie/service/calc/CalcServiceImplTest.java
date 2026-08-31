package com.example.calorie.service.calc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.example.calorie.entity.UserProfile;
import com.example.calorie.service.calc.CalcService;
import com.example.calorie.service.calc.CalcServiceImpl;

public class CalcServiceImplTest {

	private CalcService service;

	@BeforeEach
	void setUp() {

		service = new CalcServiceImpl();
	}

	// 男性の時(正常)
	@ParameterizedTest
	@CsvSource({
		"1, 2292",
		"2, 2626",
		"3, 2961",
		"4, 3295",
		"5, 3629"
		})
	void testCalcIdealCalories_Man(int activity, int expected) {

		// result = 2961
		UserProfile profile = new UserProfile();
		profile.setGender("male");
		profile.setAge(20);
		profile.setHeight(new BigDecimal("180"));
		profile.setWeight(new BigDecimal("80"));
		profile.setActivity(activity);

		int result = service.calcIdealCalories(profile);

		assertEquals(result, expected);

	}

	// 女性の時(正常)
	@ParameterizedTest
	@CsvSource({
		"1, 1460",
		"2, 1673",
		"3, 1886",
		"4, 2099",
		"5, 2312"
	})
	void testCalcIdealCalories_Woman(int activity, int expected) {

		UserProfile profile = new UserProfile();
		profile.setGender("female");
		profile.setAge(40);
		profile.setHeight(new BigDecimal("161"));
		profile.setWeight(new BigDecimal("48"));
		profile.setActivity(activity);

		int result = service.calcIdealCalories(profile);

		assertEquals(result, expected);
	}

	// 境界値(正常)
	@ParameterizedTest
	@CsvSource({
		"0, 180, 80, 3137",			// age_min
		"150, 180, 80, 1817",		// age_max
		"20, 1, 80, 1629",			// height_min
		"20, 300, 80, 3853",			// height_max
		"20, 180, 1, 1320",			// weight_min
		"20, 180, 300, 7529 "		// weight_max
	})
	void calcIdealCalories_boundaryValue(int age, String height, String weight, int expected) {

		UserProfile profile = new UserProfile();
		profile.setGender("male");
		profile.setAge(age);
		profile.setHeight(new BigDecimal(height));
		profile.setWeight(new BigDecimal(weight));
		profile.setActivity(3);

		int result = service.calcIdealCalories(profile);

		assertEquals(result, expected);
	}

}
