package com.lishuai.security.Service;

import com.lishuai.security.entity.ResponseResult;
import com.lishuai.security.entity.User;

/**
 * @author lishuai
 * @date 2022/11/29
 */
public interface ILoginUserService {

    ResponseResult login(User user);

    ResponseResult loginout();
}
