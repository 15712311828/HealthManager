package com.healthmanager.param;

import com.healthmanager.model.User;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class ForgetPasswordParam {

    @NotNull
    String code;

    @NotNull
    @Length(min = 6,max = 15,message = "密码长度需要6-15")
    String newPassword;

    @NotNull
    String email;

    public User toUser(){
        User user=new User();
        user.setPassword(newPassword);
        return user;
    }
}
