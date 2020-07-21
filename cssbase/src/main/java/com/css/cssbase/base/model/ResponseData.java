package com.css.cssbase.base.model;

import com.css.cssbase.base.constant.BaseResponseEnum;
import com.css.cssbase.base.constant.CommonResponseEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yanshuai
 * @version 1.0
 * @date 2020/07/09
 * @description 统一返回类型
 */
@Data
@NoArgsConstructor
public class ResponseData implements Serializable {
    private static final long serialVersionUID = 5930195672387867369L;

    /**
     * 业务编码
     */
    private Integer code = CommonResponseEnum.OK.value();

    /**
     * 对用户友好的提示信息, 供前端显示使用
     */
    private String message = CommonResponseEnum.OK.text();

    /**
     * 业务数据
     */
    private Map<String, Object> data = new LinkedHashMap<>();


    @JsonIgnore
    private BaseResponseEnum responseEnum;

    public static ResponseDataBuilder builder() {
        return new ResponseDataBuilder(new ResponseData());
    }

    /**
     * 默认ResponseData: code为0, message为OK
     *
     * @return
     */
    public static ResponseData build() {
        return builder().build();
    }

    public ResponseData(BaseResponseEnum responseEnum) {
        this.code = responseEnum.value();
        this.message = responseEnum.text();
    }

    public ResponseData(HttpStatus httpStatus, String message) {
        this.code = httpStatus.value();
        this.message = message;
    }

}
