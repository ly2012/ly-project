package com.example.demo.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@PostMapping("/login")
	public String login(@RequestBody Map<String,String> param)throws IOException {
		
		return "success";
	}
}
