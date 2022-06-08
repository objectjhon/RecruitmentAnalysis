package com.jhon.recruitmentanalysis.mapper;

import com.jhon.recruitmentanalysis.pojo.JobOnlinePositionCount;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface JobOnlinePositionCountMapper {

    List<JobOnlinePositionCount> findAllJobOnlinePositionCount();

    List<JobOnlinePositionCount> postFindAllJobOnlinePositionCount(@Param("city") List<String> city);

    JobOnlinePositionCount getHighestPositionCount();

    JobOnlinePositionCount getHighestPositionCountByCity(@Param("city") List<String> city);

}
