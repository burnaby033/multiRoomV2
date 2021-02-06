package com.dms.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/multiRoom")
public class login {

	@GetMapping("/login")
	public String homeController(Model model, HttpServletRequest request) {

		return "login";
	}
}
