package com.healthmanager.param;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

@Data
public class VerificationCodeParam {

    @Email(message = "邮箱不合法")
    String email;
}
