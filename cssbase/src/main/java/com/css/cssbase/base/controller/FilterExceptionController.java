package com.css.cssbase.base.controller;

import com.css.cssbase.base.constant.CommonResponseEnum;
import com.css.cssbase.base.model.ResponseData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanshuai
 * @version 1.0
 * @date 2020/07/10
 * @description 过滤器抛出异常统一处理器
 */

@RestController
@RequestMapping("/common")
public class FilterExceptionController {

    @RequestMapping(path = "/unauthorized/{message}")
    public ResponseData unauthorized(@PathVariable String message) {
        return ResponseData.builder()
                .code(CommonResponseEnum.INVALID_TOKEN.value()).message(message)
                .build();
    }
}
