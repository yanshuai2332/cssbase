package com.css.cssbase.moudles.user.controller;

import com.css.cssbase.base.model.ResponseData;
import com.css.cssbase.moudles.user.exception.UserException;
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
    public ResponseData addUser(UserCondition userCondition) throws UserException {
        userService.saveUser(userCondition);
        return ResponseData.build();
    }

    @GetMapping("/{id}")
    public ResponseData getUser(@PathVariable Long id) {
        return ResponseData.builder().data("user",userService.getUser(id)).build();
    }

    @GetMapping
    public ResponseData pageUser(UserCondition userCondition,@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
        return ResponseData.builder().data("user",userService.pageUser(userCondition,pageNo,pageSize)).build();
    }
}
