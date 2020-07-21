package com.css.cssbase.moudles.user.exception;

import com.css.cssbase.base.constant.BaseResponseEnum;
import com.css.cssbase.base.exception.BusinessException;

/**
 * @author yanshuai
 * @version 1.0
 * @date 2020/07/09
 * @description
 */
public class UserException extends BusinessException {

    public UserException(BaseResponseEnum responseEnum){
        super(responseEnum);
    }

    public UserException(String message,BaseResponseEnum responseEnum){
        super(message,responseEnum);
    }
}
