package com.healthmanager.param;

import com.healthmanager.common.UserContext;
import com.healthmanager.model.WaterData;
import lombok.Data;

import java.util.Date;


@Data
public class WaterAddParam {

    private Integer milliliter;

    public WaterData toWaterData(){
        WaterData waterData=new WaterData();
        waterData.setMilliliter(milliliter);
        waterData.setTime(new Date());
        waterData.setUserId(UserContext.getId());
        return waterData;
    }

}
