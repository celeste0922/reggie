package com.celst.reggie.dto;


import com.celst.reggie.pojo.OrderDetail;
import com.celst.reggie.pojo.Orders;
import lombok.Data;
import java.util.List;

@Data
public class OrdersDto extends Orders {

    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;
	
}
