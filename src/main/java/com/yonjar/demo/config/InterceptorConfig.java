package com.yonjar.demo.config;

import com.yonjar.demo.interceptor.TestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author luoyj
 * @Date 2021/5/8.
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //需要拦截的路径，/**表示需要拦截所有请求
//        String[]addPathPatterns={"/**"};
        String[]addPathPatterns={"/api/launcher/**"};
        //不需要拦截的路径
        /*String [] excludePathPaterns={
                "/rabbit/one_to_one/**",
                "/boot/post",
                "/boot/put",
                "/myservlet"
        };*/
        //注册一个登录拦截器
        registry.addInterceptor(new TestInterceptor())
                .addPathPatterns(addPathPatterns);
//                .excludePathPatterns(excludePathPaterns);
        //注册一个权限拦截器  如果有多个拦截器 ，只需要添加以下一行代码
        //registry.addInterceptor(new LoginInterceptor())
        // .addPathPatterns(addPathPatterns)
        // .excludePathPatterns(excludePathPatterns);

    }
}
