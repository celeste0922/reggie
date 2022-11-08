package com.celst.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.celst.reggie.dao.SetmealDishMapper;
import com.celst.reggie.dao.SetmealMapper;
import com.celst.reggie.pojo.SetmealDish;
import com.celst.reggie.service.SetmealDishService;
import org.springframework.stereotype.Service;

@Service
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements SetmealDishService {
}
