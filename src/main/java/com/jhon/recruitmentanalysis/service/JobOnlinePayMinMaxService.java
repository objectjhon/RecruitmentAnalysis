package com.jhon.recruitmentanalysis.service;

import com.jhon.recruitmentanalysis.pojo.JobOnlinePayMinMax;

import java.util.List;

public interface JobOnlinePayMinMaxService {

    List<JobOnlinePayMinMax> findAllJobOnlinePayMinMax(Integer limit);

    List<JobOnlinePayMinMax> findAllJobOnlinePayMinMax(Integer limit, List<String> city);

}
