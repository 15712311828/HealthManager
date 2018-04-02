package com.healthmanager.param;

import com.healthmanager.common.UserContext;
import com.healthmanager.model.HeartData;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class HeartUpdateParam {

    @NotNull
    @Min(value = 10,message = "参数不正确")
    public Integer rate;

    public HeartData toHeartData(){
        HeartData heartData=new HeartData();
        heartData.setRate(rate);
        heartData.setUserid(UserContext.getId());
        return heartData;
    }
}
