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

import java.io.Serializable;

/**
 * @author yanshuai
 * @version 1.0
 * @date 2020/07/23
 * @description
 */
@Data
@TableName("s_func")
@AllArgsConstructor
@NoArgsConstructor
public class Func implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField(value = "fun_code")
    private String funCode;

    @TableField(value = "name")
    private String name;

    @TableField(value = "fun_type")
    private FuncTypeEnum funType;

    @TableField(value = "parent_id")
    private Long parentId;

    @TableField(value = "order_num")
    private Long orderNum;

    @TableField(value = "del_flag")
    private DelFlagEnum delFlag;

    @TableField(value = "open_flag")
    private OpenFlagEnum openFlag;

    @TableField(value = "sys_id")
    private Long sysId;
}
