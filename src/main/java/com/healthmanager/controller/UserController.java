package com.healthmanager.controller;


import com.healthmanager.common.JsonResult;
import com.healthmanager.common.UserContext;
import com.healthmanager.param.*;
import com.healthmanager.service.UserService;
import com.healthmanager.service.VerificationCodeService;
import com.healthmanager.util.ValidUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private VerificationCodeService verificationCodeService;

    @RequestMapping("/verificationCode")
    public JsonResult verificationCode(VerificationCodeParam param){
        verificationCodeService.sendCode(param.getEmail());
        return JsonResult.success();
    }


    @RequestMapping("/signUp")
    public JsonResult signUp(SignUpParam param){
        verificationCodeService.checkCode(param.getEmail(),param.getVerificationCode());
        userService.signUp(param);
        return JsonResult.success();
    }

    @RequestMapping("/signIn")
    public JsonResult signIn(SignInParam param, HttpServletResponse response){
        userService.signIn(param,response);
        return JsonResult.success();
    }

    @RequestMapping("/info")
    public JsonResult info(){
        ValidUtil.checkLogin();
        return JsonResult.success(UserContext.get());
    }

    @RequestMapping("/changeInfo")
    public JsonResult changeInfo(ChangeInfoParam param){
        ValidUtil.checkLogin();
        userService.changeInfo(param);
        return JsonResult.success();
    }

    @RequestMapping("/changePassword")
    public JsonResult changePassword(ChangePasswordParam param){
        ValidUtil.checkLogin();
        userService.changePassword(param);
        return JsonResult.success();
    }
}
