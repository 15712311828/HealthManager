package com.healthmanager.param;

import com.healthmanager.model.User;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class ChangePasswordParam {

    @NotNull
    private String oldPassword;

    @NotNull
    @Length(min = 6,max = 15,message = "密码长度需要6-15")
    private String password;



    public User toUser(){
        User user=new User();
        user.setPassword(password);
        return user;
    }
}
