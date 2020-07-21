package com.css.cssbase.moudles.user.controller;

import com.css.cssbase.base.model.ResponseData;
import com.css.cssbase.base.model.ResponseDataBuilder;
import com.css.cssbase.base.shiro.JwtUtil;
import com.css.cssbase.moudles.user.exception.UserException;
import com.css.cssbase.moudles.user.model.LoginCondition;
import com.css.cssbase.moudles.user.model.UserCondition;
import com.css.cssbase.moudles.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public ResponseData login(LoginCondition loginCondition) throws UserException {
        String token = userService.login(loginCondition);
        return ResponseData.builder().data("token",token).build();
    }
}
