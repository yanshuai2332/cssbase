package com.css.cssbase.moudles.user.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.css.cssbase.base.constant.DelFlagEnum;
import com.css.cssbase.moudles.user.constant.GenderEnum;
import com.css.cssbase.moudles.user.constant.OpenFlagEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("s_user")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -7154577573559842865L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField(value = "real_name")
    private String realName;

    @TableField(value = "login_name")
    private String loginName;

    @TableField(value = "password")
    private String password;

    @TableField(value = "gender")
    private GenderEnum gender;

    @TableField(value = "mobile")
    private String mobile;

    @TableField(value = "user_type")
    private String userType;

    @TableField(value = "del_flag")
    private DelFlagEnum delFlag;

    @TableField(value = "open_flag")
    private OpenFlagEnum openFlag;

    @TableField(value = "org_id")
    private String orgId;

    @TableField(value = "last_login_time")
    private LocalDateTime lastLoginTime;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    @TableField(value = "salt")
    private String salt;
}
