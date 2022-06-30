package com.jhon.recruitmentanalysis.service;

import com.jhon.recruitmentanalysis.pojo.PositionName;
import java.util.List;
import java.util.Map;

public interface PositionNameService {

    List<PositionName> getAllPositionName();

    List<PositionName> getAllPositionName(List<String> city);

    PositionName getHighestPositionCount(String year,String month);

    PositionName getHighestPositionCount(String year,String month,List<String> city);

    Map<String,Object> getHighestCityPosition(String year,String month);

    Map<String,Object> getHighestCityPosition(String year,String month,List<String> city);

    List<Map<String,Object>> getCityPosition();

    List<Map<String,Object>> getCityPosition(List<String> city);

    List<Map<String,Object>> getAllPositionCount(Integer limit);

    List<Map<String,Object>> getAllPositionCount(Integer limit, List<String> city);

    List<Map<String,Object>> getPositionCityByPosition(String position);

    List<Map<String,Object>> getPositionCityByPosition(String position, List<String> city);

    List<String> getAllPosition();

}
