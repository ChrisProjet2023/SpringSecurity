package com.classrooms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.RolesAllowed;

@RestController
public class LoginController {
	
	
	@RolesAllowed("USER")
	@RequestMapping("/*")
	public String getUser() {
		return("Hello, user");
	}
	
	@RolesAllowed({"USER","ADMIN"}) //le role
	@RequestMapping("/admin") //url
	public String getAdmin() {
		return ("Hello, admin");
	}
}
