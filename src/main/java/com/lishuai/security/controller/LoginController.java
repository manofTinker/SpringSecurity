package com.lishuai.security.controller;

import com.lishuai.security.Service.ILoginUserService;
import com.lishuai.security.Service.Impl.LoginUser;
import com.lishuai.security.Service.Impl.LoginUserServiceImpl;
import com.lishuai.security.entity.ResponseResult;
import com.lishuai.security.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lishuai
 * @date 2022/11/29
 */
@Slf4j
@RestController
public class LoginController {

    @Resource
    private ILoginUserService loginUserService;


    @PostMapping("/user/login")
    public ResponseResult loginUser(@RequestBody User user){//@RequestBody User user

        ResponseResult login = loginUserService.login(user);

        return login;
    }

    @GetMapping("/loginout")
    public ResponseResult loginout(){

        ResponseResult loginout = loginUserService.loginout();

        return loginout;
    }

}
