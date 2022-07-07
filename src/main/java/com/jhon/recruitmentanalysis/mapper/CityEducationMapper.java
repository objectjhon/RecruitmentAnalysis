package com.jhon.recruitmentanalysis.mapper;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CityEducationMapper {

    List<Map> getCityEducation();

    List<Map> getCityEducationByCity(@Param("city") List<String> city);

    List<Map> getEducationBySalary(@Param("minSalary") BigDecimal minSalary,
                                   @Param("maxSalary") BigDecimal maxSalary);

    List<Map> getCityBySalary(@Param("minSalary") BigDecimal minSalary,
                              @Param("maxSalary") BigDecimal maxSalary);

    List<Map> getPositionNumByCity();

    List<Map> getPositionNumByCityList(@Param("city") List<String> map);

}
