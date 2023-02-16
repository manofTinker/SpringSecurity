package com.lishuai.security.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lishuai.security.entity.User;
import com.lishuai.security.mapper.MenuMapper;
import com.lishuai.security.mapper.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author lishuai
 * @date 2022/11/29
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, s));

        if(Objects.isNull(user)){
            throw new RuntimeException("用户密码错误");
        }

        List<String> menuList = menuMapper.selectPermsByUserId(user.getId());

        //TODO 查询对应的权限信息

        return new LoginUser(user,menuList);
    }
}
