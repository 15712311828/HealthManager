package com.healthmanager.param;

import com.healthmanager.common.UserContext;
import com.healthmanager.model.RunningData;
import lombok.Data;

import java.util.Date;

@Data
public class RunningAddParam {

    private Integer step;

    private Date time;

    public RunningData toRunningData(){
        RunningData data=new RunningData();
        data.setUserId(UserContext.getId());
        data.setStep(step);
        data.setTime(time);
        return data;
    }
}
