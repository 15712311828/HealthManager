package com.healthmanager.param;


import com.healthmanager.common.UserContext;
import com.healthmanager.model.BodyData;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class BodyUpdateParam {

    @NotNull
    private BigDecimal height;

    @NotNull
    private BigDecimal weight;

    public BodyData toBodyData(){
        BodyData bodyData=new BodyData();
        bodyData.setHeight(height);
        bodyData.setWeight(weight);
        bodyData.setUserid(UserContext.getId());
        return bodyData;
    }
}
