package com.healthmanager.Interceptor;

import com.healthmanager.common.UserContext;
import com.healthmanager.dao.UserMapper;
import com.healthmanager.model.User;
import com.healthmanager.model.UserExample;
import com.healthmanager.service.UserService;
import com.healthmanager.util.LoginUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies == null){
            return true;
        }
        String name= LoginUtil.getLoginName(cookies);
        UserExample example=new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<User> users = userMapper.selectByExample(example);
        if(users.size()>=1){
            UserContext.set(users.get(0));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        UserContext.remove();
    }
}
