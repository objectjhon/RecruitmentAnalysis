package com.jhon.recruitmentanalysis.service;

import com.jhon.recruitmentanalysis.pojo.PositionValue;

import java.util.List;

public interface PositionValueService {

    List<PositionValue> getAllPositionValue(String query);

    Integer addPositionValue(PositionValue positionValue);

    PositionValue getPositionValueById(Integer id);

    Integer updatePositionValue(PositionValue positionValue);

    Integer updatePositionStatus(Integer id,Integer status);

    Integer deletePositionValue(Integer id);

}
