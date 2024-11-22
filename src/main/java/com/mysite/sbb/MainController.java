package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
    public String root() {
		return "main_page"; // main_page.html을 반환
    }
  @GetMapping("/trick")
    public String trick() {
      return "trick";
  }