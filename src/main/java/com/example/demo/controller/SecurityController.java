package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

//	アクセスエラー
	@GetMapping("/access-error")
	public String showAccessError() {
		
		return "access-error";
	}
	
//	ログイン画面表示
	@GetMapping("/login")
	public String showLoginPage() {
				
		return "login";
	}
	
//	ログインエラー画面表示
	@GetMapping("/login-error")
	public String showLoginFail() {
				
		return "login";
	}
	
}
