package com.healthmanager.dao;

import com.healthmanager.model.SleepData;
import com.healthmanager.model.SleepDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SleepDataMapper {
    long countByExample(SleepDataExample example);

    int deleteByExample(SleepDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SleepData record);

    int insertSelective(SleepData record);

    List<SleepData> selectByExample(SleepDataExample example);

    SleepData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SleepData record, @Param("example") SleepDataExample example);

    int updateByExample(@Param("record") SleepData record, @Param("example") SleepDataExample example);

    int updateByPrimaryKeySelective(SleepData record);

    int updateByPrimaryKey(SleepData record);
}