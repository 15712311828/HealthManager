package com.healthmanager.service;

import com.healthmanager.common.UserContext;
import com.healthmanager.dao.UserMapper;
import com.healthmanager.exception.BusinessException;
import com.healthmanager.model.User;
import com.healthmanager.model.UserExample;
import com.healthmanager.param.*;
import com.healthmanager.util.LoginUtil;
import com.healthmanager.util.ValidUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public void signUp(SignUpParam param){

        UserExample nameExample=new UserExample();
        UserExample.Criteria nameCriteria = nameExample.createCriteria();
        nameCriteria.andNameEqualTo(param.getName());
        long nameResult = userMapper.countByExample(nameExample);
        ValidUtil.checkNonExist(nameResult,"用户名已被注册");

        UserExample emailExample=new UserExample();
        UserExample.Criteria emailCriteria = emailExample.createCriteria();
        emailCriteria.andEmailEqualTo(param.getEmail());
        long emailResult = userMapper.countByExample(emailExample);
        ValidUtil.checkNonExist(emailResult,"邮箱已被注册");

        userMapper.insert(param.toUser());
    }

    public void signIn(SignInParam param, HttpServletResponse response) {
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andPasswordEqualTo(param.getPassword());
        if(param.getName()!=null){
            criteria.andNameEqualTo(param.getName());
        }
        else if(param.getEmail()!=null){
            criteria.andEmailEqualTo(param.getEmail());
        }
        else{
            throw new BusinessException();
        }
        List<User> users = userMapper.selectByExample(userExample);
        ValidUtil.checkExist(users,"用户名或密码错误");
        LoginUtil.keepLogin(users.get(0).getEmail(),response);
    }

    public void changeInfo(ChangeInfoParam param){
        if(!param.getName().equals(UserContext.getName())) {
            UserExample nameExample = new UserExample();
            UserExample.Criteria nameCriteria = nameExample.createCriteria();
            nameCriteria.andNameEqualTo(param.getName());
            long nameResult = userMapper.countByExample(nameExample);
            ValidUtil.checkNonExist(nameResult, "用户名已被注册");
        }

        User user = param.toUser();
        user.setId(UserContext.getId());
        userMapper.updateByPrimaryKeySelective(user);
    }

    public void changePassword(ChangePasswordParam param){
        User user = param.toUser();
        user.setId(UserContext.getId());
        userMapper.updateByPrimaryKeySelective(user);
    }

    public void checkEmailExit(String email){
        UserExample emailExample=new UserExample();
        UserExample.Criteria emailCriteria = emailExample.createCriteria();
        emailCriteria.andEmailEqualTo(email);
        long emailResult = userMapper.countByExample(emailExample);
        if(emailResult==0){
            throw new BusinessException("该邮箱尚未注册");
        }
    }

    public void forgetPassword(ForgetPasswordParam param){
        User user = param.toUser();
        user.setId(UserContext.getId());
        userMapper.updateByPrimaryKeySelective(user);
    }
}
