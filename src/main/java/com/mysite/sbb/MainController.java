package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
    public String root() {
        return "redirect:/question/list";

    }
	@GetMapping("//")
    public String root1() {
        return "main_page"; // main_page.html을 반환
    }
}

