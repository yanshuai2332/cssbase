package com.css.cssbase.moudles.user.model;
import com.css.cssbase.moudles.user.constant.GenderEnum;
import lombok.Data;

@Data
public class UserCondition {

    private String realName;

    private String loginName;

    private String password;

    private GenderEnum gender;

    private String mobile;

    private String orgId;
}
