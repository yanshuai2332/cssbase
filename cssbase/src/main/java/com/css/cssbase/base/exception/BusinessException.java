package com.css.cssbase.base.exception;


import com.css.cssbase.base.constant.BaseResponseEnum;


/**
 * @author yanshuai
 * @version 1.0
 * @date 2020/07/09
 * @description
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 6552388885921678083L;

	protected final BaseResponseEnum responseEnum;

	public BusinessException(BaseResponseEnum responseEnum) {
		this.responseEnum = responseEnum;
	}

	public BusinessException(String message, BaseResponseEnum responseEnum) {
		super(message);
		this.responseEnum = responseEnum;
	}

	public BaseResponseEnum responseEnum() {
		return responseEnum;
	}
}
