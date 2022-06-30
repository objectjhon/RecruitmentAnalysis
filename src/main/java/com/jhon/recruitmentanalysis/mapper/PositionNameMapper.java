package com.jhon.recruitmentanalysis.mapper;

import com.jhon.recruitmentanalysis.pojo.PositionName;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface PositionNameMapper {

    List<PositionName> getAllPositionName();

    List<PositionName> getAllPositionNameByCity(@Param("city") List<String> city);

    PositionName getHighestPositionCount(@Param("year") String year,@Param("month") String month);

    PositionName getHighestPositionCountByCity(@Param("year") String year,
                                               @Param("month") String month,
                                               @Param("city") List<String> city);

    Map<String,Object> getHighestCityPosition(@Param("year") String year,@Param("month") String month);

    Map<String,Object> getHighestCityPositionByCity(@Param("year") String year,
                                                    @Param("month") String month,
                                                    @Param("city") List<String> city);

    List<Map<String,Object>> getCityPosition();

    List<Map<String,Object>> getCityPositionByCity(@Param("city") List<String> city);

    List<Map<String,Object>> getAllPositionCount(@Param("limit") Integer limit);

    List<Map<String,Object>> getAllPositionCountByCity(@Param("limit") Integer limit, @Param("city") List<String> city);

    List<Map<String,Object>> getPositionCityByPosition(@Param("position") String position);

    List<Map<String,Object>> getPositionCityByPositionCity(@Param("position") String position, @Param("city") List<String> city);

    List<String> getAllPosition();

}
