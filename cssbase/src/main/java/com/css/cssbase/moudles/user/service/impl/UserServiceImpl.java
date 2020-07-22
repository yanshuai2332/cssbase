package com.css.cssbase.moudles.user.service.impl;



import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.css.cssbase.base.constant.DelFlagEnum;
import com.css.cssbase.base.shiro.JwtUtil;
import com.css.cssbase.base.shiro.UserContext;
import com.css.cssbase.base.shiro.UserDetails;
import com.css.cssbase.base.util.RedisUtil;
import com.css.cssbase.moudles.user.constant.OpenFlagEnum;
import com.css.cssbase.moudles.user.constant.UserResponseEnum;
import com.css.cssbase.moudles.user.entity.User;
import com.css.cssbase.moudles.user.exception.UserException;
import com.css.cssbase.moudles.user.model.LoginCondition;
import com.css.cssbase.moudles.user.model.UserCondition;
import com.css.cssbase.moudles.user.model.UserDTO;
import com.css.cssbase.moudles.user.repository.UserMapper;
import com.css.cssbase.moudles.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;


@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    /** 登录用户Token令牌缓存KEY前缀 */
    public static final String PREFIX_USER_TOKEN  = "prefix_user_token_";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String login(LoginCondition loginCondition) throws UserException {
        String loginName = loginCondition.getLoginName();
        String passWord = loginCondition.getPassword();
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getLoginName, loginName));
        if (user == null) {
            throw new UserException("该用户不存在", UserResponseEnum.INVALID_ID);
        }
        String saltPassword = getSaltPassword(passWord,user.getSalt());
        if(!saltPassword.equals(user.getPassword())){
            throw new UserException("密码不正确",UserResponseEnum.ERROR_USERNAME_OR_PASSWORD);
        }
        String token = JwtUtil.sign(loginName, passWord);
        redisUtil.set(PREFIX_USER_TOKEN + token, token);
        redisUtil.expire(PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME*2 / 1000);
        return token;
    }

    @Override
    public void saveUser(UserCondition userCondition) throws UserException {
        List<User> sameLoginNameUsers = list(Wrappers.<User>lambdaQuery().eq(User::getLoginName, userCondition.getLoginName()));
        if(!CollectionUtils.isEmpty(sameLoginNameUsers)){
            throw new UserException(UserResponseEnum.REPEAT_MOBILE);
        }
        User user = new User();
        user.setRealName(userCondition.getRealName());
        user.setLoginName(userCondition.getLoginName());
        String salt = UUID.randomUUID().toString().replaceAll("-","");
        String saltPassword = getSaltPassword(userCondition.getPassword(), salt);
        user.setSalt(salt);
        user.setPassword(saltPassword);
        user.setGender(userCondition.getGender());
        user.setMobile(userCondition.getMobile());
        user.setUserType(userCondition.getUserType());
        user.setDelFlag(DelFlagEnum.NOT_DELETE);
        user.setOpenFlag(OpenFlagEnum.OPEN);
        //TODO 对orgId进行检测
        user.setOrgId(userCondition.getOrgId());
        userMapper.insert(user);
    }

    private String getSaltPassword(String password, String salt) {
        SimpleHash simpleHash = new SimpleHash("MD5", password, salt, 1024);
        return simpleHash.toHex();
    }

    @Override
    public User getUser(Long id) {
        return getById(id);
    }

    @Override
    public IPage<UserDTO> pageUser(UserCondition userCondition,Integer pageNo, Integer pageSize) {
        Page<UserDTO> page = new Page<UserDTO>(pageNo, pageSize);
        return userMapper.pageUser(page,userCondition);
    }

}
