package com.celst.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.celst.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //路径匹配器
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        Long id = Thread.currentThread().getId();
//        log.info("线程id:{}",id);
        log.info("拦截请求：{}",request.getRequestURI());
        //1.获取本次请求uri
        String requestUri = request.getRequestURI();
        String[] urls = new String[]{//不需要拦截的请求路径
                "/employee/login",
                "employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };
        //2.判断请求是否需要拦截
        boolean check = check(urls,requestUri);

        //3.不需要处理，直接放行
        if(check){
            filterChain.doFilter(request,response);
            log.info("本次请求{}不需要处理",requestUri);
            return;
        }

        //4-1.判断登陆状态，登陆则放行-后台
        if(request.getSession().getAttribute("employee") != null){
            filterChain.doFilter(request,response);

            Long empId = (Long) request.getSession().getAttribute("employee");

            log.info("管理员{}已登陆",empId);
            return;
        }

        //4-2.判断登陆状态，登陆则放行-用户
        if(request.getSession().getAttribute("user") != null){
            filterChain.doFilter(request,response);
            Long userId = (Long) request.getSession().getAttribute("user");
            log.info("用户{}已登陆",userId);

            return;
        }
        //5.未登录
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        log.info("未登录");
        return;

//        log.info("拦截请求：{}",request.getRequestURI());
//        filterChain.doFilter(request,response);//全部放行
    }
    public boolean check(String[] urls,String requestUri){
        for (String url :urls){
            boolean match = PATH_MATCHER.match(url, requestUri);
            if(match){
                return true;
            }
        }
        return false;

    }

}
