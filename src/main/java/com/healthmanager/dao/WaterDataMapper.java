package com.healthmanager.dao;

import com.healthmanager.model.WaterData;
import com.healthmanager.model.WaterDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WaterDataMapper {
    long countByExample(WaterDataExample example);

    int deleteByExample(WaterDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WaterData record);

    int insertSelective(WaterData record);

    List<WaterData> selectByExample(WaterDataExample example);

    WaterData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WaterData record, @Param("example") WaterDataExample example);

    int updateByExample(@Param("record") WaterData record, @Param("example") WaterDataExample example);

    int updateByPrimaryKeySelective(WaterData record);

    int updateByPrimaryKey(WaterData record);
}