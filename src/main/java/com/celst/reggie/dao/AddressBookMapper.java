package com.celst.reggie.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.celst.reggie.pojo.AddressBook;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
