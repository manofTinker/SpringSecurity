package com.lishuai.security.Service.Impl;

import com.lishuai.security.Service.ILoginUserService;
import com.lishuai.security.config.WebSecurityConfig;
import com.lishuai.security.entity.ResponseResult;
import com.lishuai.security.entity.User;
import com.lishuai.security.util.JwtUtil;
import com.lishuai.security.util.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author lishuai
 * @date 2022/11/29
 */
@Service
public class LoginUserServiceImpl implements ILoginUserService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCache redisCache;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @Override
    public ResponseResult login(User user) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());

        Authentication authenticate = authenticationManager.authenticate(token);

        if(Objects.isNull(authenticate)){
            throw new RuntimeException("error");
        }

        LoginUser loginuser = (LoginUser)authenticate.getPrincipal();
        String id = loginuser.getUser().getId().toString();

        String jwt = JwtUtil.createJWT(id);

        redisCache.setCacheObject("login:"+id,loginuser);

        HashMap<String, String> map = new HashMap<>();
        map.put("token",jwt);

        return new ResponseResult(200,"登陆成功",map);
    }

    /**
     * 注销
     *
     * @return
     */
    @Override
    public ResponseResult loginout() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        System.out.println("loginUser:"+loginUser);

        Long id = loginUser.getUser().getId();

        System.out.println("id:"+id);

        redisCache.deleteObject("login"+id);

        return new ResponseResult(200,"注销成功");
    }
}
