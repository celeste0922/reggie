package com.celst.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.celst.reggie.pojo.Category;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
