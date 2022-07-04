package com.jhon.recruitmentanalysis.mapper;

import com.jhon.recruitmentanalysis.pojo.PositionValue;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PositionValueMapper {

    List<PositionValue> getAllPositionValue(@Param("query") String query);

    Integer addPositionValue(PositionValue positionValue);

    PositionValue getPositionValueById(@Param("id") Integer id);

    Integer updatePositionValue(PositionValue positionValue);

    Integer updatePositionStatus(@Param("id") Integer id,
                                 @Param("status") Integer status);

    Integer deletePositionValue(@Param("id") Integer id);

}
