package com.celst.reggie.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.celst.reggie.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
