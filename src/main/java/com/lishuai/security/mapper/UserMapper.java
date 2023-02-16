package com.lishuai.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lishuai.security.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lishuai
 * @date 2022/11/29
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
