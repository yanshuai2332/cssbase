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

    private String userType;

    private String orgId;

    public void ofNullable(){
        this.realName = realName == null?"":realName;
        this.loginName = loginName == null?"":loginName;
        this.password = password == null?"":password;
        this.mobile = mobile == null?"":mobile;
        this.userType = userType == null?"":userType;
    }
}
