package com.celst.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.celst.reggie.common.CustomException;
import com.celst.reggie.dao.CategoryMapper;
import com.celst.reggie.pojo.Category;
import com.celst.reggie.pojo.Dish;
import com.celst.reggie.pojo.Setmeal;
import com.celst.reggie.service.CategoryService;
import com.celst.reggie.service.DishService;
import com.celst.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    @Override
    @Transactional
    public void remove(Long id) {//删除前进行判断
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count = dishService.count(dishLambdaQueryWrapper);
        //查询分类是否关联菜品,关联则抛异常
        if(count>0){//抛出业务异常
            throw new CustomException("当前分类下关联了菜品，无法删除");
        }
        //是否关联套餐，关联则抛异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count1 = setmealService.count(setmealLambdaQueryWrapper);
        if(count1>0){
            throw new CustomException("当前分类下关联了套餐，无法删除");
        }
        //删除
        super.removeById(id);
    }
}
