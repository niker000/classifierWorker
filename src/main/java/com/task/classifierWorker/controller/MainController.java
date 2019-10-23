package com.task.classifierWorker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/app/mainPage")
    public String showMainPage() {
        return "main.html";
    }
}
