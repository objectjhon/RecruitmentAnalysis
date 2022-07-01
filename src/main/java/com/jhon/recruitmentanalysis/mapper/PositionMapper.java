package com.jhon.recruitmentanalysis.mapper;

import com.jhon.recruitmentanalysis.pojo.Position;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PositionMapper {

    List<Position> getAll(@Param("year") String year,
                          @Param("month") String month,
                          @Param("day") String day,
                          @Param("city") String city,
                          @Param("salaryMin") BigDecimal salaryMin,
                          @Param("salaryMax") BigDecimal salaryMax);

    List<Position> getAllByKeyWord(@Param("year") String year,
                                   @Param("month") String month,
                                   @Param("day") String day,
                                   @Param("city") String city,
                                   @Param("salaryMin") BigDecimal salaryMin,
                                   @Param("salaryMax") BigDecimal salaryMax,
                                   @Param("keyword") List<String> keyword);

}
