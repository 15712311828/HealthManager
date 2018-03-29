package com.healthmanager.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SignInParam {

    String name;

    @NotNull
    String password;

    String email;
}
