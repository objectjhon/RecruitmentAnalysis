package com.jhon.recruitmentanalysis.service;

import com.jhon.recruitmentanalysis.pojo.JobOnlinePositionCount;

import java.util.List;

public interface JobOnlinePositionCountService {

    List<JobOnlinePositionCount> findAllJobOnlinePositionCount();

    List<JobOnlinePositionCount> postFindAllJobOnlinePositionCount(List<String> city);

}
