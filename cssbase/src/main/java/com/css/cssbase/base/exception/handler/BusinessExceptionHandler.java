package com.css.cssbase.base.exception.handler;


import com.css.cssbase.base.exception.BusinessException;
import com.css.cssbase.base.model.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yanshuai
 * @version 1.0
 * @date 2020/07/09
 * @description
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class BusinessExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseData businessExceptionHandler(BusinessException e) {
        log.error("业务异常: " + e.responseEnum().text(), e);
        return ResponseData.builder()
                .result(e.responseEnum())
                .build();
    }
}
