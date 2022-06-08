package com.jhon.recruitmentanalysis.mapper;

import com.jhon.recruitmentanalysis.pojo.CityCount;
import com.jhon.recruitmentanalysis.pojo.PositionCityKey;
import com.jhon.recruitmentanalysis.pojo.PositionCityKeyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PositionCityKeyMapper {

    List<PositionCityKey> findAllPositionCityKey(@Param("limit") Integer limit);

    List<PositionCityKey> postFindAllPositionCityKey(@Param("limit") Integer limit, @Param("city") List<String> city);

    List<PositionCityKeyVo> getAllPositionCityKey();

    List<PositionCityKeyVo> postGetAllPositionCityKey(@Param("city") List<String> city);

    List<PositionCityKeyVo> getAllPositionCity(@Param("limit") Integer limit);

    List<PositionCityKeyVo> postGetAllPositionCity(@Param("limit") Integer limit, @Param("city") List<String> city);

    String[] getKey(@Param("position") String position);

    String[] getAllKey();

    String[] getAllKeyByCity(@Param("city") List<String> city);

    CityCount getHighestCityCount();

    CityCount postGetHighestCityCount(@Param("city") List<String> city);

}
