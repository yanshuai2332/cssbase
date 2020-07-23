package com.css.cssbase.base.constant;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author yanshuai
 * @version 1.0
 * @date 2020/07/13
 * @description
 */
public enum OpenFlagEnum implements CommonEntityEnum {

    OPEN(1, "打开"),
    CLOSE(2, "关闭");

    private Integer value;

    private String text;

    OpenFlagEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    @Override
    public int value() {
        return value;
    }

    /**
     * mybatis Mapper中的方法参数如果为枚举的话, 会调用toString()方法获取枚举值, 用于ognl表达式判断
     *
     * @return
     */
    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @SuppressWarnings("unused")
    @JsonCreator
    public static OpenFlagEnum valueOf(Integer value) {
        return CommonEntityEnum.valueOf(OpenFlagEnum.class, value);
    }
}
