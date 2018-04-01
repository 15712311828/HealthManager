package com.healthmanager.param;

import com.healthmanager.model.User;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ChangeInfoParam {

    @NotNull
    @Length(min = 3,max = 15,message = "用户名长度需要3-15")
    private String name;

    @NotNull
    @Min(value = 0)
    @Max(value = 1)
    private Integer sex;

    @NotNull
    private String sign;

    public User toUser(){
        User user=new User();
        user.setName(name);
        user.setSex(sex);
        user.setSign(sign);
        return user;
    }
}
