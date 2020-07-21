package com.css.cssbase.base.model;

import com.css.cssbase.moudles.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yanshuai
 * @version 1.0
 * @date 2020/07/09
 * @description 统一返回类型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {

    private Long userId;

    private String userName;

    private String password;

    private String salt;

    private int status;

    public LoginUser(User user){
        this.userId = user.getId();
        this.userName = user.getLoginName();
        this.password = user.getPassword();
        this.salt = user.getSalt();
    }
}
