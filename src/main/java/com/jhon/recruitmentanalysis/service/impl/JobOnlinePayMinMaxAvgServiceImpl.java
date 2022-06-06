package com.jhon.recruitmentanalysis.service.impl;

import com.jhon.recruitmentanalysis.mapper.JobOnlinePayMinMaxAvgMapper;
import com.jhon.recruitmentanalysis.pojo.JobOnlinePayMinMaxAvg;
import com.jhon.recruitmentanalysis.service.JobOnlinePayMinMaxAvgService;
import jdk.nashorn.internal.scripts.JO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JobOnlinePayMinMaxAvgServiceImpl implements JobOnlinePayMinMaxAvgService {

    @Resource
    private JobOnlinePayMinMaxAvgMapper jobOnlinePayMinMaxAvgMapper;

    @Override
    public List<JobOnlinePayMinMaxAvg> findAllJobOnlinePayMinMaxAvg(Integer limit) {
        return jobOnlinePayMinMaxAvgMapper.findAllJobOnlinePayMinMaxAvg(limit);
    }

    @Override
    public List<JobOnlinePayMinMaxAvg> findAllJobOnlinePayMinMaxAvg(Integer limit, List<String> city) {
        return jobOnlinePayMinMaxAvgMapper.postFindAllJobOnlinePayMinMaxAvg(limit, city);
    }

}
