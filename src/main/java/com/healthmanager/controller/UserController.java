package com.healthmanager.controller;


import com.google.common.base.Preconditions;
import com.healthmanager.common.JsonResult;
import com.healthmanager.common.UserContext;
import com.healthmanager.param.*;
import com.healthmanager.service.UserService;
import com.healthmanager.service.VerificationCodeService;
import com.healthmanager.util.ValidUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private VerificationCodeService verificationCodeService;

    @RequestMapping("/verificationCode")
    public JsonResult verificationCode(@RequestBody @Valid VerificationCodeParam param){
        verificationCodeService.sendCode(param.getEmail());
        return JsonResult.success();
    }


    @RequestMapping("/signUp")
    public JsonResult signUp(@RequestBody @Valid SignUpParam param){
        verificationCodeService.checkCode(param.getEmail(),param.getVerificationCode());
        userService.signUp(param);
        return JsonResult.success();
    }

    @RequestMapping("/signIn")
    public JsonResult signIn(@RequestBody @Valid SignInParam param, HttpServletResponse response){
        userService.signIn(param,response);
        return JsonResult.success();
    }

    @RequestMapping("/info")
    public JsonResult info(){
        ValidUtil.checkLogin();
        return JsonResult.success(UserContext.get());
    }

    @RequestMapping("/changeInfo")
    public JsonResult changeInfo(@RequestBody @Valid ChangeInfoParam param){
        ValidUtil.checkLogin();
        userService.changeInfo(param);
        return JsonResult.success();
    }

    @RequestMapping("/changePassword")
    public JsonResult changePassword(@RequestBody @Valid ChangePasswordParam param){
        ValidUtil.checkLogin();
        Preconditions.checkArgument(param.getOldPassword().equals(UserContext.get().getPassword()),"旧密码不正确");
        userService.changePassword(param);
        return JsonResult.success();
    }

    @RequestMapping("/forgetPassword")
    public JsonResult forgetPassword(@RequestBody @Valid ForgetPasswordParam param){
        userService.checkEmailExit(param.getEmail());
        verificationCodeService.checkCode(param.getEmail(),param.getCode());
        userService.forgetPassword(param);
        return JsonResult.success();
    }
}
