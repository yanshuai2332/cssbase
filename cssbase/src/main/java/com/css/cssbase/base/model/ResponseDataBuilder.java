package com.css.cssbase.base.model;

import com.css.cssbase.base.constant.BaseResponseEnum;

/**
 * @author yanshuai
 * @version 1.0
 * @date 2020/07/09
 * @description
 */
public class ResponseDataBuilder {

    private ResponseData responseData;

    public ResponseDataBuilder(ResponseData responseData) {
        this.responseData = responseData;
    }

    public ResponseDataBuilder code(Integer code) {
        responseData.setCode(code);
        return this;
    }

    public ResponseDataBuilder message(String userMessage) {
        responseData.setMessage(userMessage);
        return this;
    }

    public ResponseDataBuilder result(BaseResponseEnum responseEnum) {
        responseData.setCode(responseEnum.value());
        responseData.setMessage(responseEnum.text());
        return this;
    }

    public ResponseDataBuilder data(String key, Object value) {
        responseData.getData().put(key, value);
        return this;
    }

    public ResponseData build() {
        return responseData;
    }

}
