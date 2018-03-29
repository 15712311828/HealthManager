package com.healthmanager.model;

import java.util.Date;

public class WaterData {
    private Integer id;

    private Integer userid;

    private Integer milliliter;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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