package com.task.classifierWorker.controller;

import com.task.classifierWorker.dto.UserDTO;
import com.task.classifierWorker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegistrationController {
    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public String createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return "redirect:/login";
    }
}
