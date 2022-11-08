package com.celst.reggie.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.celst.reggie.pojo.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
}
