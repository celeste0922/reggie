package com.celst.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.celst.reggie.dto.DishDto;
import com.celst.reggie.pojo.Dish;
import org.springframework.stereotype.Service;

@Service
public interface DishService extends IService<Dish> {
    //新增菜品同时插入口味dish表和dish_flavor
    public void  saveWithFlavor(DishDto dishDto);

    public DishDto getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDto dishDto);
}
