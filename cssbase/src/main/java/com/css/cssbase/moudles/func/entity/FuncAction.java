package com.css.cssbase.moudles.func.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.css.cssbase.base.constant.DelFlagEnum;
import com.css.cssbase.base.constant.OpenFlagEnum;
import com.css.cssbase.moudles.func.constant.FuncTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yanshuai
 * @version 1.0
 * @date 2020/07/23
 * @description
 */
@Data
@TableName("s_func_action")
@AllArgsConstructor
@NoArgsConstructor
public class FuncAction {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField(value = "fun_code")
    private String funCode;

    @TableField(value = "action_code")
    private String actionCode;

    @TableField(value = "action_name")
    private String actionName;

    @TableField(value = "open_flag")
    private OpenFlagEnum openFlag;

    @TableField(value = "sys_id")
    private Long sysId;
}
