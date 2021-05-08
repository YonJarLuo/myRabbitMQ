package com.yonjar.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author luoyj
 * @Date 2021/5/8.
 * 主动编写需要重写的方法
 */
public class TestInterceptor implements HandlerInterceptor {

    @Override //进入Controller之前执行该方法
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录拦截的业务逻辑
        System.out.println("-------进入到自定义拦截器--------------");
        System.out.println(request.getRequestURI());
        StringBuffer requestURL = request.getRequestURL();
        System.out.println(requestURL.toString());
        /*Object object = request.getSession().getAttribute("user");
        if (object == null) {
            System.out.println("用户没有登录");
            return false;
        }*/

        //继续提交请求，false 请求不提交了
        return true;

    }
}
