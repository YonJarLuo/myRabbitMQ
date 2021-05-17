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

        String requestURI = request.getRequestURI();
        String[] split = requestURI.split("/");
        System.out.println(split.length);
        for (String s : split) {
            System.out.println(s);
        }

        System.out.println("----------");
        System.out.println(split[3]);
        /*Object object = request.getSession().getAttribute("user");
        if (object == null) {
            System.out.println("用户没有登录");
            return false;
        }*/
        if ("test".equals(split[3])){
            //这里拦截到自己想拦截的接口，可以做业务处理
//            throw new Exception("抛异常");
//            return false;
        }

        //继续提交请求，false 请求不提交了
        return true;

    }
}
