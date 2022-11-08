package com.celst.reggie.common;

public class CustomException extends RuntimeException{//自定义业务异常
    public CustomException(String msg){
        super(msg);
    }
}
