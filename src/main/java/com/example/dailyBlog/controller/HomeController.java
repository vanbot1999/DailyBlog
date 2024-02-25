package com.example.dailyBlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(@RequestParam(name = "continue", required = false) String continueParam, Model model) {
        // 处理请求逻辑
        // 例如，您可以根据 continue 参数的值来决定重定向到不同的页面或进行其他操作

        // 将数据添加到模型中，以便在视图中使用
        model.addAttribute("continueParam", continueParam);

        // 返回视图名称（例如，一个Thymeleaf模板的名称）
        return "home";  // 假设您有一个名为 "home.html" 的模板
    }
}
