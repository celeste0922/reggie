package com.celst.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.celst.reggie.dao.DishFlavorMapper;
import com.celst.reggie.dao.DishMapper;
import com.celst.reggie.pojo.DishFlavor;
import com.celst.reggie.service.DishFlavorService;
import com.celst.reggie.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
