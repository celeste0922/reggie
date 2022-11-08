package com.celst.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Autowired
    private HttpServletRequest httpServletRequest;
    @Override
    public void insertFill(MetaObject metaObject) {//插入时自动填充
        log.info("公共字段自动insert");
        log.info(metaObject.toString());

        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime",LocalDateTime.now());
        if(httpServletRequest.getSession().getAttribute("user")!=null){
            metaObject.setValue("createUser",httpServletRequest.getSession().getAttribute("user"));
            metaObject.setValue("updateUser",httpServletRequest.getSession().getAttribute("user"));
        }else {
            metaObject.setValue("createUser", httpServletRequest.getSession().getAttribute("employee"));
            metaObject.setValue("updateUser", httpServletRequest.getSession().getAttribute("employee"));
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {//更新时自动填充
        log.info("公共字段自动update");
        log.info(metaObject.toString());
        Long id = Thread.currentThread().getId();
        log.info("线程id:{}",id);

        metaObject.setValue("updateTime",LocalDateTime.now());
        if(httpServletRequest.getSession().getAttribute("user")!=null){
            metaObject.setValue("updateUser",httpServletRequest.getSession().getAttribute("user"));
        }
        else{
            metaObject.setValue("updateUser",httpServletRequest.getSession().getAttribute("employee"));
        }
    }
}
