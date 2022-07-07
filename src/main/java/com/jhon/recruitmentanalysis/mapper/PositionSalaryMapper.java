package com.jhon.recruitmentanalysis.mapper;

import com.jhon.recruitmentanalysis.pojo.PositionSalary;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PositionSalaryMapper {

    List<PositionSalary> getAllPositionSalary(@Param("position") String position);

    List<PositionSalary> getAllPositionSalaryByCity(@Param("position") String position,
                                                    @Param("city") List<String> city);

    List<Map<String,Object>> getPositionCount(@Param("year") String year,
                                              @Param("month") String month);

    List<Map<String,Object>> getPositionCountByCity(@Param("year") String year,
                                                    @Param("month") String month,
                                                    @Param("city") List<String> city);

    List<Map<String,Object>> getPositionSalary();

    List<Map<String,Object>> getPositionSalaryByCity(@Param("city") List<String> city);

    Map<String,Object> getHighestSalaryPosition(@Param("year") String year,@Param("month") String month);

    Map<String,Object> getHighestSalaryPositionByCity(@Param("year") String year,
                                                      @Param("month") String month,
                                                      @Param("city") List<String> city);

    List<Map> getPositionBySalary(@Param("minSalary") BigDecimal minSalary,
                                  @Param("maxSalary") BigDecimal maxSalary);

}
