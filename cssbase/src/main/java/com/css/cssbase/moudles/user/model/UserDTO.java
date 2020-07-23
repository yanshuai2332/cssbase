package com.css.cssbase.moudles.user.model;

import com.css.cssbase.moudles.user.constant.GenderEnum;
import com.css.cssbase.base.constant.OpenFlagEnum;
import lombok.Data;

/**
 * @author yanshuai
 * @version 1.0
 * @date 2020/07/21
 * @description
 */
@Data
public class UserDTO {

    private Long id;

    private String realName;

    private GenderEnum gender;

    private String mobile;

    private String userType;

    private String orgId;

    private OpenFlagEnum openFlag;
}
