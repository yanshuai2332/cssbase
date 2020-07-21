package com.css.cssbase.base.constant;

/**
 * @author yanshuai
 * @version 1.0
 * @date 2020/07/09
 * @description
 */
public enum CommonResponseEnum implements BaseResponseEnum{
    OK(0,"OK"),
    INTERNAL_ERROR(500,"服务器异常"),

    INVALID_TOKEN(43000,"不合法的token"),
    JSON_PARSE_ERROR(45000, "不合法的请求JSON格式"),
    MISSING_PATH_VARIABLE(45001, "缺少请求URL路径参数"),
    MISSING_REQUEST_PARAMETER(45002, "缺少请求query参数"),
    METHOD_ARGUMENT_TYPE_MIS_MATCH(45003, "不合法的请求参数类型"),
    CONSTRAINT_VIOLATION(45004, "请求参数验证未通过"),
    MEDIA_TYPE_NOT_SUPPORTED(45005, "不合法的请求Content-Type"),
    REQUEST_METHOD_NOT_SUPPORTED(45006, "不合法的请求方法"),
    NO_HANDLER_FOUND(45007, "请求资源不存在");

    private Integer value;

    private String text;

    @Override
    public Integer value() {
        return value;
    }

    @Override
    public String text() {
        return text;
    }

    CommonResponseEnum(Integer value, String text){
        this.value = value;
        this.text = text;
    }
}
