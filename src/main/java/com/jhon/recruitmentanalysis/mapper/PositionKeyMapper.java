package com.jhon.recruitmentanalysis.mapper;

import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PositionKeyMapper {

    List<Map<String,Object>> getAllKey();

    List<Map<String,Object>> getAllKeyByCity(@Param("city") List<String> city);

    List<Map<String,Object>> getPositionKey(@Param("limit") Integer limit,
                                            @Param("position") String position);

    List<Map<String,Object>> getPositionKeyByCity(@Param("limit") Integer limit,
                                                  @Param("position") String position,
                                                  @Param("city") List<String> city);

    String[] getKeyBySalaryPosition(@Param("position") String position,
                                    @Param("salaryMin") BigDecimal salaryMin,
                                    @Param("salaryMax") BigDecimal salaryMax);

    String[] getKeyBySalaryPositionCity(@Param("position") String position,
                                        @Param("salaryMin") BigDecimal salaryMin,
                                        @Param("salaryMax") BigDecimal salaryMax,
                                        @Param("city") List<String> city);

    List<String> getAllKeyWord(@Param("position") List<String> position);

    List<Map<String,Object>> getKeyByPosition(@Param("position") List<String> position);

    List<Map> getKeyWordBySalary(@Param("salary") String salary);

}
