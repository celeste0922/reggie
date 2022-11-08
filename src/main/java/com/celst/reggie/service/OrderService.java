package com.celst.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.celst.reggie.dao.OrderMapper;
import com.celst.reggie.pojo.Orders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface OrderService extends IService<Orders> {
    public void submit(Orders orders, Long userId);
}
