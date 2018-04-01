package com.healthmanager.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class LoginUtil {

    public static final String KEY="asdfqwer";

    public static void keepLogin(String userName,HttpServletResponse response){
        Cookie nameCookie=new Cookie("_u",CryptUtil.aesEncrypt(userName,KEY));
        nameCookie.setPath("/");
        Cookie timeCookie=new Cookie("_t",CryptUtil.aesEncrypt(System.currentTimeMillis()+"",KEY));
        timeCookie.setPath("/");
        response.addCookie(nameCookie);
        response.addCookie(timeCookie);
    }

    public static String getLoginName(Cookie[] cookies,HttpServletResponse response){
        String name=null;
        Long time=null;
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("_u")){
                try {
                    name = CryptUtil.aesDecrypt(cookie.getValue(), KEY);
                }
                catch (Exception e){
                    name=null;
                }
            }
            if(cookie.getName().equals("_t")){
                try {
                    time = Long.valueOf(CryptUtil.aesDecrypt(cookie.getValue(), KEY));
                }
                catch (Exception e){
                    time=null;
                }
            }
        }
        if(time==null||name==null){
            return null;
        }
        if(System.currentTimeMillis()-time<3000000){
            Cookie timeCookie=new Cookie("_t",CryptUtil.aesEncrypt(System.currentTimeMillis()+"",KEY));
            timeCookie.setPath("/");
            response.addCookie(timeCookie);
            return name;
        }
        return null;
    }
}
