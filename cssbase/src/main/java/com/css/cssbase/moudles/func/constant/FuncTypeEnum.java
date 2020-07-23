package com.css.cssbase.moudles.func.constant;

import com.css.cssbase.base.constant.CommonEntityEnum;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author yanshuai
 * @version 1.0
 * @date 2020/07/23
 * @description
 */
public enum FuncTypeEnum implements CommonEntityEnum {

    BUSINESS(1, "业务"),
    SYSTEM(2, "系统");

    private Integer value;

    private String text;

    FuncTypeEnum(Integer value, String text) {
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
    public static FuncTypeEnum valueOf(Integer value) {
        return CommonEntityEnum.valueOf(FuncTypeEnum.class, value);
    }
}
