package com.lishuai.security.handler;

import com.alibaba.fastjson.JSON;
import com.lishuai.security.entity.ResponseResult;
import com.lishuai.security.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权相关处理
 *
 * @author lishuai
 * @date 2022/11/30
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {

        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), "权限不足");

        WebUtils.renderString(response, JSON.toJSONString(result));

    }
}
