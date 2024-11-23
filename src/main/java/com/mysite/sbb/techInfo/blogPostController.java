package com.mysite.sbb.techInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class blogPostController {

    @Autowired
    private blogPostRepository blogPostRepository;

    @GetMapping("/techInfo")
    public String getBlogPosts(Model model) {
        List<blogPost> blogPosts = blogPostRepository.findAll();
        model.addAttribute("blogPosts", blogPosts);
        return "techInfo";
    }
}
