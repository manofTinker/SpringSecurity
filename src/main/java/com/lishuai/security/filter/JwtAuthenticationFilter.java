package com.lishuai.security.filter;

import com.lishuai.security.Service.Impl.LoginUser;
import com.lishuai.security.util.JwtUtil;
import com.lishuai.security.util.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author lishuai
 * @date 2022/11/29
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private RedisCache redisCache;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("token");

        System.out.println("token"+token);

        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        String subject = null;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            subject = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String key ="login:" + subject;

        LoginUser loginUser = redisCache.getCacheObject(key);

        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录");
        }

        //TODO 获取权限信息并放入

        //从redis中获取到loginUser并拿到权限信息
        Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        filterChain.doFilter(request, response);
    }
}
