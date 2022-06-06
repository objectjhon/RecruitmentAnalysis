package com.jhon.recruitmentanalysis.mapper;

import com.jhon.recruitmentanalysis.pojo.JobOnlinePay;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobOnlinePayMapper {

    List<JobOnlinePay> findAllJobOnlinePay(@Param("limit") Integer limit);

    List<JobOnlinePay> postFindAllJobOnlinePay(@Param("limit") Integer limit, @Param("city") List<String> city);

}
