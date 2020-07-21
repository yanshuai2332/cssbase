package com.css.cssbase.base.shiro;

import com.css.cssbase.moudles.user.entity.User;
import lombok.Data;

/**
 * @author yanshuai
 * @version 1.0
 * @date 2020/07/10
 * @description
 */
@Data
public class UserDetails {

    private Long id;

    private String name;

    private int status;

    public UserDetails(User user){
        this.id = user.getId();
        this.name = user.getLoginName();
    }
}
