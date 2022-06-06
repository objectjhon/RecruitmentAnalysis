package com.jhon.recruitmentanalysis.service;

import com.jhon.recruitmentanalysis.pojo.PositionCityKey;
import com.jhon.recruitmentanalysis.pojo.PositionCityKeyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PositionCityKeyService {

    List<PositionCityKey> findAllPositionCityKey(Integer limit);

    List<PositionCityKey> postFindAllPositionCityKey(Integer limit,List<String> city);

    List<PositionCityKeyVo> getAllPositionCityKey();

    List<PositionCityKeyVo> postGetAllPositionCityKey(List<String> city);


    List<PositionCityKeyVo> getAllPositionCity(Integer limit);

    List<PositionCityKeyVo> postGetAllPositionCity(Integer limit, List<String> city);

    Map<String,Integer> getKey(String position);

    Map<String,Integer> getAllKey();

}