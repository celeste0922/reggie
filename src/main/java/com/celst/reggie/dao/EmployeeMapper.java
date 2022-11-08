package com.celst.reggie.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.celst.reggie.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
