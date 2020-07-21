package com.css.cssbase.base.exception.handler;

import com.css.cssbase.base.constant.CommonResponseEnum;
import com.css.cssbase.base.model.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * @author yanshuai
 * @version 1.0
 * @date 2020/07/09
 * @description
 */
@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseData defaultExceptionHandler(Exception e) {
		log.error("Internal Server Error", e);
		return ResponseData.builder()
			.result(CommonResponseEnum.INTERNAL_ERROR)
			.build();

	}
}
