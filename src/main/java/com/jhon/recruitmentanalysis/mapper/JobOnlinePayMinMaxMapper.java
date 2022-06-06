package com.jhon.recruitmentanalysis.mapper;

import com.jhon.recruitmentanalysis.pojo.JobOnlinePayMinMax;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobOnlinePayMinMaxMapper {

    List<JobOnlinePayMinMax> findAllJobOnlinePayMinMax(@Param("limit") Integer limit);

    List<JobOnlinePayMinMax> postFindAllJobOnlinePayMinMax(@Param("limit") Integer limit, @Param("city") List<String> city);

}
