package com.healthmanager.util;

import org.junit.Test;

public class EmailUtilTest {

    @Test
    public void test(){
        EmailUtil.sendMail("a15712311828@outlook.com","验证码","<h1>您好，您的验证码为663452</h1>");
    }
}
