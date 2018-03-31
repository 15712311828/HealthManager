package com.healthmanager.param;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

@Data
public class VerificationCodeParam {

    @Email(message = "邮箱不合法")
    @NotNull
    String email;
}
