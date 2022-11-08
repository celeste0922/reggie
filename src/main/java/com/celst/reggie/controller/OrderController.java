package com.celst.reggie.controller;

import com.celst.reggie.common.R;
import com.celst.reggie.pojo.Orders;
import com.celst.reggie.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders, HttpServletRequest request){
        Long userId = (Long) request.getSession().getAttribute("user");
        orderService.submit(orders,userId);
        return R.success("下单成功");
    }
}
