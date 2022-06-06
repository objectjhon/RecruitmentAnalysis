package com.jhon.recruitmentanalysis.service;

import com.jhon.recruitmentanalysis.pojo.JobOnlinePayMinMaxAvg;

import java.util.List;

public interface JobOnlinePayMinMaxAvgService {

    List<JobOnlinePayMinMaxAvg> findAllJobOnlinePayMinMaxAvg(Integer limit);

    List<JobOnlinePayMinMaxAvg> findAllJobOnlinePayMinMaxAvg(Integer limit, List<String> city);

}
