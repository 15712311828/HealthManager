package com.healthmanager.model;

import java.util.Date;

public class WaterData {
    private Integer id;

    private Integer userId;

    private Integer milliliter;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMilliliter() {
        return milliliter;
    }

    public void setMilliliter(Integer milliliter) {
        this.milliliter = milliliter;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}