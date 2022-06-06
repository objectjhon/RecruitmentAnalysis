package com.jhon.recruitmentanalysis.mapper;

import com.jhon.recruitmentanalysis.pojo.JobOnlinePayMinMaxAvg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobOnlinePayMinMaxAvgMapper {

    List<JobOnlinePayMinMaxAvg> findAllJobOnlinePayMinMaxAvg(@Param("limit") Integer limit);

    List<JobOnlinePayMinMaxAvg> postFindAllJobOnlinePayMinMaxAvg(@Param("limit") Integer limit, @Param("city") List<String> city);

}
