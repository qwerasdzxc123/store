package com.wj.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: WangJin
 * @description
 * @date: 2021/9/7 17:33
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse
            response, Object handler) throws Exception {
        if (request.getSession().getAttribute("uid") == null) {
            response.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }
}

