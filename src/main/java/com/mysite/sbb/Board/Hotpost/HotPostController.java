package com.mysite.sbb.Board.Hotpost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class HotPostController {

    @Autowired
    private HotPostService hotPostService;

    // 핫게시물 페이지
    @GetMapping("/hotPosts")
    public String getHotPosts(Model model) {
        List<HotPost> hotPosts = hotPostService.getAllPosts();
        model.addAttribute("hotPosts", hotPosts);
        return "hotPosts"; // hotPosts.html 렌더링
    }

    // 제목 검색 API
    @GetMapping("/api/hotPost/search")
    @ResponseBody
    public List<HotPost> searchHotPosts(@RequestParam("keyword") String keyword) {
        return hotPostService.searchPosts(keyword);
    }
}
