package com.lishuai.security;

import com.lishuai.security.entity.User;
import com.lishuai.security.mapper.MenuMapper;
import com.lishuai.security.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lishuai
 * @date 2022/11/29
 */
@SpringBootTest
public class SecurityTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MenuMapper menuMapper;

    @Test
    public void SecurityTest01(){

        List<User> users = userMapper.selectList(null);
        System.out.println(users);

    }

    @Test
    public void passwordEncoding(){

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("1234"));
        System.out.println(bCryptPasswordEncoder.matches("12341","$2a$10$1fP4sxtk606sLsuNqkz5N.naLS4UaSQadbYHwWu3NrK4QiMwdCWBq"));

    }

    @Test
    public void menuMapperTest(){
        System.out.println(menuMapper.selectPermsByUserId(2L));
    }
}
