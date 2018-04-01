package com.healthmanager.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class CryptUtilTest {

    @Test
    public void test(){
        String s = CryptUtil.aesEncrypt("haoyan.zhang", "asdfqwer");
        log.info(s);
        String ss = CryptUtil.aesDecrypt(s, "asdfqwer");
        log.info(ss);
    }
}
