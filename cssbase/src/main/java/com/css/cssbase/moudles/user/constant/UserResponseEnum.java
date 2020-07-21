package com.css.cssbase.moudles.user.constant;


import com.css.cssbase.base.constant.BaseResponseEnum;


/**
 * @author yanshuai
 * @version 1.0
 * @date 2020/07/09
 * @description
 */
public enum UserResponseEnum implements BaseResponseEnum {
    REPEAT_MOBILE(31000, "重复的用户名"),
    INVALID_ID(31001, "用户不存在"),
    ERROR_USERNAME_OR_PASSWORD(31002, "用户名或密码错误"),
    ERROR_USER_LOCK(31003, "账户无法登录"),
    ;

    private int value;

    private String text;

    @Override
    public Integer value() {
        return value;
    }

    @Override
    public String text() {
        return text;
    }

    UserResponseEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }
}
