package com.adun.mapper;

import com.adun.entities.User;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author ADun
 * @date 2023/2/12 15:10
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    public User getUserByCustomWapper(@Param(Constants.WRAPPER)Wrapper wrapper);
}
