package com.css.cssbase.base.exception.handler;


import com.css.cssbase.base.constant.BaseResponseEnum;
import com.css.cssbase.base.constant.CommonResponseEnum;
import com.css.cssbase.base.model.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * @author yanshuai
 * @version 1.0
 * @date 2020/07/09
 * @description
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class SpringMvcBadRequestExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseData httpMessageConvertExceptionHanlder(HttpMessageNotReadableException e) {
        return responseData(CommonResponseEnum.JSON_PARSE_ERROR, e);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseData missingPathVariableExceptionHanlder(MissingPathVariableException e) {
        return responseData(CommonResponseEnum.MISSING_PATH_VARIABLE, e);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseData requestParameterExceptionHandler(MissingServletRequestParameterException e) {
        return responseData(CommonResponseEnum.MISSING_REQUEST_PARAMETER, e);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseData methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        return responseData(CommonResponseEnum.METHOD_ARGUMENT_TYPE_MIS_MATCH, e);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseData constraintViolationExceptionHandler(ConstraintViolationException e) {
        log(e);
        return ResponseData.builder()
                .code(CommonResponseEnum.CONSTRAINT_VIOLATION.value())
                .message(e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).findFirst().get())
                .build();
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseData httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException e) {
        return responseData(CommonResponseEnum.MEDIA_TYPE_NOT_SUPPORTED, e);
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseData httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        return responseData(CommonResponseEnum.REQUEST_METHOD_NOT_SUPPORTED, e);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseData noHandlerFoundExceptionHandler(NoHandlerFoundException e) {
        return responseData(CommonResponseEnum.NO_HANDLER_FOUND, e);
    }


    private void log(Throwable e) {
        log.error(e.getMessage(), e);
    }

    private ResponseData responseData(BaseResponseEnum responseEnum, Throwable e) {
        log(e);
        return ResponseData.builder()
                .result(responseEnum)
                .build();
    }
}
