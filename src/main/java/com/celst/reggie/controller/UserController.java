package com.celst.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.celst.reggie.common.R;
import com.celst.reggie.pojo.User;
import com.celst.reggie.service.UserService;
import com.celst.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpServletRequest request){
        //获取手机号
        String phone = user.getPhone();
        //生成随机4位验证码
        if(phone != null){
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("验证码：{}",code);
            //调用api发短信
//            SMSUtils.sendMessage("武科大外卖",phone,code);
            //验证码保存到session
            request.getSession().setAttribute(phone,code);
            return R.success("验证码发送成功");
        }

        return R.error("验证码发送失败");
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpServletRequest request){
        log.info(map.toString());

        //获取手机号
        String phone = map.get("phone").toString();

        //获取验证码
        String code = map.get("code").toString();
        //从session获取保存的验证码
        Object codeInSession = request.getSession().getAttribute(phone);

        //比对验证码
        if(codeInSession != null && codeInSession.equals(code)) {
            //比对成功,登陆成功
            //判断是否为新用户
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(queryWrapper);
            if(user == null){
                //新用户则保存
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            request.getSession().setAttribute("user",user.getId());
            return R.success(user);
        }

        return R.error("登陆失败");
    }
}
