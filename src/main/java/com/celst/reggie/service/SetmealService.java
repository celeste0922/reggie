package com.celst.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.celst.reggie.dto.SetmealDto;
import com.celst.reggie.pojo.Setmeal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SetmealService extends IService<Setmeal> {
    public void saveWithDish(SetmealDto setmealDto);
    public void removeWithDish(List<Long> ids);
}
