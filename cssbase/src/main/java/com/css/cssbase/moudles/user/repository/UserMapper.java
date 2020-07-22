package com.css.cssbase.moudles.user.repository;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.css.cssbase.moudles.user.entity.User;
import com.css.cssbase.moudles.user.model.UserCondition;
import com.css.cssbase.moudles.user.model.UserDTO;
import org.apache.ibatis.annotations.Param;


public interface UserMapper extends BaseMapper<User> {

    IPage<UserDTO> pageUser(Page page,@Param("userCondition") UserCondition userCondition);
}
