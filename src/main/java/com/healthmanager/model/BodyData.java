package com.healthmanager.model;

import java.math.BigDecimal;

public class BodyData {
    private Integer id;

    private Integer userid;

    private BigDecimal htight;

    private BigDecimal weight;

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

    public BigDecimal getHtight() {
        return htight;
    }

    public void setHtight(BigDecimal htight) {
        this.htight = htight;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}