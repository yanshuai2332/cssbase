package com.css.cssbase.moudles.user.controller;

import com.css.cssbase.base.model.ResponseData;
import com.css.cssbase.moudles.user.model.UserCondition;
import com.css.cssbase.moudles.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseData addUser(UserCondition userCondition) {
        userService.saveUser(userCondition);
        return ResponseData.build();
    }

    @GetMapping("/{id}")
    public ResponseData getUser(@PathVariable Long id) {
        return ResponseData.builder().data("user",userService.getUser(id)).build();
    }
}
