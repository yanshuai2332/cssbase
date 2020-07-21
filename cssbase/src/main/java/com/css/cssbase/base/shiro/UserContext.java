package com.css.cssbase.base.shiro;

/**
 * @author yanshuai
 * @version 1.0
 * @date 2020/07/10
 * @description
 */
public class UserContext {

    private static final ThreadLocal<UserDetails> CURRENT_USER = new ThreadLocal<>();

    public static void setCurrentUser(UserDetails user) {
        CURRENT_USER.set(user);
    }

    public static UserDetails getCurrentUser() {
        return CURRENT_USER.get();
    }

    public static void removeCurrentUser() {
        CURRENT_USER.remove();
    }
}
