package com.healthmanager.common;


import com.healthmanager.model.User;

public class UserContext{

    private static ThreadLocal<User> USER = new ThreadLocal<User>();

    public static void set(User user){
        USER.set(user);
    }

    public static User get(){
        return USER.get();
    }

    public static void remove(){
        if(USER.get()!=null) {
            USER.remove();
        }
    }

    public static int getId(){
        User user = USER.get();
        return user==null?null:user.getId();
    }

    public static String getName(){
        User user = USER.get();
        return user==null?null:user.getName();
    }

    public static int getSex(){
        User user = USER.get();
        return user==null?null:user.getSex();
    }

    public static String getEmail(){
        User user = USER.get();
        return user==null?null:user.getEmail();
    }

    public static String getSign(){
        User user = USER.get();
        return user==null?null:user.getSign();
    }
}
