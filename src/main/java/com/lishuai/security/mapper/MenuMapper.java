package com.lishuai.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lishuai.security.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lishuai
 * @date 2022/11/30
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);

}
