package com.jhon.recruitmentanalysis.mapper;

import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;
import java.util.List;

public interface PositionKeyMapper {

    String[] getAllKey();

    String[] getAllKeyByCity(@Param("city") List<String> city);

    String[] getPositionKey(@Param("position") String position);

    String[] getPositionKeyByCity(@Param("position") String position,@Param("city") List<String> city);

    String[] getKeyBySalaryPosition(@Param("position") String position,
                                    @Param("salaryMin") BigDecimal salaryMin,
                                    @Param("salaryMax") BigDecimal salaryMax);

    String[] getKeyBySalaryPositionCity(@Param("position") String position,
                                        @Param("salaryMin") BigDecimal salaryMin,
                                        @Param("salaryMax") BigDecimal salaryMax,
                                        @Param("city") List<String> city);

}
