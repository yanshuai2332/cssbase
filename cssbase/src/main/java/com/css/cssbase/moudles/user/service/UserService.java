package com.css.cssbase.moudles.user.service;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.css.cssbase.moudles.user.entity.User;
import com.css.cssbase.moudles.user.exception.UserException;
import com.css.cssbase.moudles.user.model.LoginCondition;
import com.css.cssbase.moudles.user.model.UserCondition;
import com.css.cssbase.moudles.user.model.UserDTO;

public interface UserService extends IService<User> {

    String login(LoginCondition loginCondition) throws UserException;

    void saveUser(UserCondition userCondition) throws UserException;

    User getUser(Long id);

    IPage<UserDTO> pageUser(UserCondition userCondition, Integer pageNo, Integer pageSize);
}
