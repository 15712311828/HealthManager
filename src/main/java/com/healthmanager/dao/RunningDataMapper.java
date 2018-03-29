package com.healthmanager.dao;

import com.healthmanager.model.RunningData;
import com.healthmanager.model.RunningDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RunningDataMapper {
    long countByExample(RunningDataExample example);

    int deleteByExample(RunningDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RunningData record);

    int insertSelective(RunningData record);

    List<RunningData> selectByExample(RunningDataExample example);

    RunningData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RunningData record, @Param("example") RunningDataExample example);

    int updateByExample(@Param("record") RunningData record, @Param("example") RunningDataExample example);

    int updateByPrimaryKeySelective(RunningData record);

    int updateByPrimaryKey(RunningData record);
}