package com.example.calorie.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class CsrfTokenController {

	//CSRFトークンをJSONで返す
		@GetMapping("/csrf-token")
		public CsrfToken csrfToken(HttpServletRequest request) {
		    return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		}
}
